package com.example.goalgiver.ui.main.schedule

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.FragmentScheduleBinding
import com.example.goalgiver.ui.main.goal.GoalSetItem
import com.example.goalgiver.ui.teamcertificationalarm.RequestAlarmActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: ToDoAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
        initViews()

        // 현재 날짜 설정
        setCurrentMonthYearTitle()

        // SharedPreferences에서 데이터 로드
        loadGoalsFromPrefs()

        // goalList를 관찰하여 변화가 있을 때 리사이클러뷰를 업데이트
        sharedViewModel.goalList.observe(viewLifecycleOwner) { goalList ->
            if (goalList != null) {
                filterItemsByDate(null) // 날짜 선택이 없으면 모든 항목을 표시
                binding.calendarView.addDecorator(DotDecorator(requireContext(), goalList.map { it.startdate }))
            }
        }
    }

    private fun initViews() {
        todoAdapter = ToDoAdapter()
        binding.todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }

        binding.calendarView.setOnDateChangedListener { _, date, _ ->
            val selectedDate = date.date?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            selectedDate?.let { filterItemsByDate(it) }
        }

        binding.calendarView.setOnMonthChangedListener { _, date -> updateMonthYearTitle(date) }
        binding.calendarView.setTitleFormatter(DateFormatTitleFormatter(DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.KOREAN)))
        binding.calendarView.setWeekDayLabels(arrayOf("월", "화", "수", "목", "금", "토", "일"))
    }

    private fun loadGoalsFromPrefs() {
        val gson = Gson()
        val json = sharedPreferences.getString("goal_list", null)
        val type = object : TypeToken<ArrayList<GoalSetItem>>() {}.type
        val goalList: ArrayList<GoalSetItem>? = gson.fromJson(json, type)

        if (goalList != null) {
            val toDoItems = goalList.map { goalItem ->
                ToDoItem(
                    scheduleIcon = goalItem.goalIcon,
                    title = goalItem.goalTitle,
                    startdate = goalItem.goalStartDate,
                    enddate = goalItem.goalEndDate,
                    status = "인증",
                    certification = goalItem.goalCertificationCheck
                )
            }
            sharedViewModel.setGoalList(toDoItems)
        }
    }

    private fun setCurrentMonthYearTitle() {
        // 현재 날짜를 가져와서 textMonthYear에 설정
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.getDefault())
        binding.textMonthYear.text = currentDate.format(formatter)
    }

    private fun updateMonthYearTitle(calendarDay: CalendarDay) {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.getDefault())
        binding.textMonthYear.text = calendarDay.date?.format(formatter)
    }

    private fun filterItemsByDate(selectedDate: String?) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val filteredItems = if (selectedDate == null) {
            sharedViewModel.goalList.value ?: emptyList()
        } else {
            val selectedLocalDate = LocalDate.parse(selectedDate, formatter)
            (sharedViewModel.goalList.value ?: emptyList()).filter {
                val startDate = LocalDate.parse(it.startdate, formatter)
                val endDate = LocalDate.parse(it.enddate, formatter)
                !selectedLocalDate.isBefore(startDate) && !selectedLocalDate.isAfter(endDate)
            }
        }

        updateRecyclerViewVisibility(filteredItems)
    }

    private fun updateRecyclerViewVisibility(filteredItems: List<ToDoItem>) {
        if (filteredItems.isNotEmpty()) {
            todoAdapter.submitList(filteredItems)
            todoAdapter.notifyDataSetChanged()
            binding.todoRecyclerView.visibility = View.VISIBLE
            binding.placeholderLayout.visibility = View.GONE
        } else {
            todoAdapter.submitList(emptyList())
            todoAdapter.notifyDataSetChanged()
            binding.todoRecyclerView.visibility = View.GONE
            binding.placeholderLayout.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
