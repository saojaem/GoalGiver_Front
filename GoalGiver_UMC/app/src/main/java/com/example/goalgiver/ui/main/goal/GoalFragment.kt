package com.example.goalgiver.ui.main.goal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoalBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.goalgiver.ui.goaldetail.GoalDetailActivity
import com.example.goalgiver.ui.main.schedule.SharedViewModel
import com.example.goalgiver.ui.main.schedule.ToDoItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GoalFragment : Fragment() {

    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var goalList: ArrayList<GoalSetItem> //원본데이터
    private lateinit var filteredGoalList: ArrayList<GoalSetItem> // 필터링된 데이터
    private lateinit var teamChooseLauncher: ActivityResultLauncher<Intent>
    private lateinit var sharedPreferences: SharedPreferences
    private val goalTimerViewModel: GoalTimerViewModel by activityViewModels()

    private var isCompletedFilter: Boolean = false
    private var personTeamFilter: Int = 1 // 1: 개인, 2: 팀
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoalBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
        filteredGoalList = arrayListOf()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonListeners()

        // ActivityResultLauncher 초기화
        teamChooseLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val goalItem: GoalSetItem? = result.data?.getParcelableExtra("goalItem")

                if (goalItem == null) {
                    Log.e("GoalFragment", "GoalSetItem is null!")
                } else {
                    goalList.add(goalItem)
                    saveGoalListToPrefs()
                    binding.goalFragmentRecyclerView.adapter?.notifyDataSetChanged()

                    // 시간이 설정된 경우에만 타이머를 시작
                    if (goalItem.remainingTime > 0) {
                        goalTimerViewModel.startTimer(goalItem)
                    }
                    val newToDoItem = ToDoItem(
                        scheduleIcon = goalItem.goalIcon,
                        title = goalItem.goalTitle,
                        startdate = goalItem.goalStartDate,
                        enddate = goalItem.goalEndDate,
                        status = "인증",
                        certification = goalItem.goalCertificationCheck
                    )
                    sharedViewModel.addGoal(newToDoItem)
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), AddGoalMain::class.java)
            teamChooseLauncher.launch(intent)
        }

        goalList = loadGoalListFromPrefs() ?: arrayListOf(

            //GoalSetItem("🎯", "Goal 1", "D-10", "100", "Progress 50%", 50,"2024-08-19","2024-08-30","매주 1일",1,1,0L)

        )
        setupRecyclerView()
        saveGoalListToSharedViewModel()

        // 시간이 설정된 항목에 대해서만 타이머 시작
        goalList.forEach { goalItem ->
            if (goalItem.remainingTime > 0) {
                goalTimerViewModel.startTimer(goalItem)
            }
        }

        goalTimerViewModel.goalTimers.observe(viewLifecycleOwner) { timers ->
            timers.forEach { (goalItem, remainingTime) ->
                // remainingTime이 변경되었을 때 GoalSetItem의 값을 업데이트
                goalItem.remainingTime = remainingTime
                if (remainingTime > 0) {
                    goalItem.goalDDay = formatTimeRemaining(remainingTime)
                } else {
                    goalItem.goalDDay = "Time's up!"
                }

                // goalList와 filteredGoalList 모두 업데이트
                updateGoalInFilteredGoalList(goalItem)
                binding.goalFragmentRecyclerView.adapter?.notifyItemChanged(goalList.indexOf(goalItem))
            }
        }
        binding.personalButton.performClick()
        binding.inProgressButton.performClick()
    }

    private fun applyFilters() {
        filteredGoalList = goalList.filter {
            val isCompleted = calculateDaysRemaining(it.goalEndDate) < 0
            val matchesCompletionFilter = if (isCompletedFilter) isCompleted else !isCompleted
            val matchesPersonTeamFilter = it.getPersonTeam() == personTeamFilter
            matchesCompletionFilter && matchesPersonTeamFilter
        } as ArrayList<GoalSetItem>
        (binding.goalFragmentRecyclerView.adapter as GoalSetAdapter).updateGoalList(filteredGoalList)
    }
    private fun calculateDaysRemaining(endDateStr: String): Int {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val endDate = sdf.parse(endDateStr)

            val today = Calendar.getInstance()
            today.set(Calendar.HOUR_OF_DAY, 0)
            today.set(Calendar.MINUTE, 0)
            today.set(Calendar.SECOND, 0)
            today.set(Calendar.MILLISECOND, 0)

            val diffInMillis = endDate!!.time - today.timeInMillis
            (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    private fun updateGoalInFilteredGoalList(goalItem: GoalSetItem) {
        val index = filteredGoalList.indexOfFirst { it.goalTitle == goalItem.goalTitle }
        if (index != -1) {
            if (goalItem.remainingTime > 0) {
                filteredGoalList[index] = goalItem
                (binding.goalFragmentRecyclerView.adapter as GoalSetAdapter).notifyItemChanged(index)
            }
        }
    }

    private fun saveGoalListToSharedViewModel() {
        val toDoItems = goalList.map { goalItem ->
            ToDoItem(
                scheduleIcon = goalItem.goalIcon,
                title = goalItem.goalTitle,
                startdate = goalItem.goalStartDate,
                enddate = goalItem.goalEndDate,
                status = "인증",  // You can modify this according to your logic
                certification = goalItem.goalCertificationCheck
            )
        }
        sharedViewModel.setGoalList(toDoItems)
    }

    private fun setupButtonListeners() {
        binding.personalButton.setOnClickListener {
            personTeamFilter = 1
            updateButtonSelection(binding.personalButton, binding.teamButton)
            applyFilters()  // personCheck 값이 1인 데이터만 표시
        }

        binding.teamButton.setOnClickListener {
            personTeamFilter = 2
            updateButtonSelection(binding.teamButton, binding.personalButton)
            applyFilters() // personCheck 값이 2인 데이터만 표시
        }

        binding.inProgressButton.setOnClickListener {
            isCompletedFilter = false
            updateButtonSelection(binding.inProgressButton, binding.completedButton)
            applyFilters()
        }

        binding.completedButton.setOnClickListener {
            isCompletedFilter = true
            clearGoalListFromPrefs()
            updateButtonSelection(binding.completedButton, binding.inProgressButton)
            applyFilters()
        }
    }

    private fun updateButtonSelection(selectedButton: Button, otherButton: Button) {
        selectedButton.setBackgroundResource(R.drawable.circle_choose_btn)
        selectedButton.setTextColor(resources.getColor(R.color.white))
        otherButton.setBackgroundResource(R.drawable.circle_btn)
        otherButton.setTextColor(resources.getColor(R.color.textcolor))
    }

    private fun setupRecyclerView() {
        val adapter = GoalSetAdapter(requireContext(), goalList)
        binding.goalFragmentRecyclerView.adapter = adapter
        binding.goalFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // RecyclerView item click listener 설정
        adapter.setOnItemClickListener(object : GoalSetAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
//                val clickedItem = goalList[position]
                val clickedItem = filteredGoalList[position]
                Log.d("GoalFragmentItemClick", "Clicked: ${clickedItem.goalIcon}")
                val intent = Intent(requireContext(), GoalDetailActivity::class.java)
                intent.putExtra("goalItem", clickedItem)
                startActivity(intent)
            }
        })
    }

    private fun formatTimeRemaining(diffInMillis: Long): String {
        val hours = diffInMillis / (1000 * 60 * 60) % 24
        val minutes = (diffInMillis / (1000 * 60)) % 60
        val seconds = (diffInMillis / 1000) % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveGoalListToPrefs() {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(goalList)
        editor.putString("goal_list", json)
        editor.apply()
    }

    private fun loadGoalListFromPrefs(): ArrayList<GoalSetItem>? {
        val gson = Gson()
        val json = sharedPreferences.getString("goal_list", null)
        val type = object : TypeToken<ArrayList<GoalSetItem>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun clearGoalListFromPrefs() {
        val editor = sharedPreferences.edit()
        editor.remove("goal_list")
        editor.apply()
    }
}