package com.example.goalgiver.ui.main.goal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoalBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.goalgiver.ui.goaldetail.GoalDetailActivity

class GoalFragment : Fragment() {

    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!

    private lateinit var goalList: ArrayList<GoalSetItem>
    private lateinit var teamChooseLauncher: ActivityResultLauncher<Intent>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoalBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
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
                        startCountDownTimer(goalItem)
                    }
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), AddGoalMain::class.java)
            teamChooseLauncher.launch(intent)
        }

        goalList = loadGoalListFromPrefs() ?: arrayListOf(

            GoalSetItem("🎯", "Goal 1", "D-10", "100", "Progress 50%", 50,"2024-8-19","2024-8-30","매주 1일",1,1, "월드비전", 0L)
        )

        setupRecyclerView()

        // 시간이 설정된 항목에 대해서만 타이머 시작
        goalList.forEach { goalItem ->
            if (goalItem.remainingTime > 0) {
                startCountDownTimer(goalItem)
            }
        }
    }

    private fun setupButtonListeners() {
        binding.personalButton.setOnClickListener {
            updateButtonSelection(binding.personalButton, binding.teamButton)
        }

        binding.teamButton.setOnClickListener {
            updateButtonSelection(binding.teamButton, binding.personalButton)
        }

        binding.inProgressButton.setOnClickListener {
            updateButtonSelection(binding.inProgressButton, binding.completedButton)
        }

        binding.completedButton.setOnClickListener {
            updateButtonSelection(binding.completedButton, binding.inProgressButton)
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
                val clickedItem = goalList[position]
                Log.d("GoalFragment", "Clicked: ${clickedItem}")
                val intent = Intent(requireContext(), GoalDetailActivity::class.java)
                intent.putExtra("goalItem", clickedItem)
                startActivity(intent)
            }
        })
    }

    private fun startCountDownTimer(goal: GoalSetItem) {
        val timer = object : CountDownTimer(goal.remainingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                goal.remainingTime = millisUntilFinished

                // 남은 시간을 타이머 형태로 표시
                goal.goalDDay = formatTimeRemaining(millisUntilFinished)

                binding.goalFragmentRecyclerView.adapter?.notifyItemChanged(goalList.indexOf(goal))
            }

            override fun onFinish() {
                goal.remainingTime = 0
                goal.goalDDay = "Time's up!"

                binding.goalFragmentRecyclerView.adapter?.notifyItemChanged(goalList.indexOf(goal))
            }
        }
        timer.start()
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
}
