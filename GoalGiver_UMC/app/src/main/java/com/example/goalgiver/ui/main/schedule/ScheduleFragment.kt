package com.example.goalgiver.ui.main.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.FragmentScheduleBinding
import com.example.goalgiver.ui.teamcertificationalarm.RequestAlarmActivity
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        // SharedViewModel 데이터 초기화
        initializeData()

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

    private fun initializeData() {
        // SharedViewModel이 초기화되거나 데이터를 가져오도록 합니다.
        if (sharedViewModel.goalList.value.isNullOrEmpty()) {
            loadInitialData()
        }
    }

    private fun loadInitialData() {
        // 여기서 실제로 필요한 데이터를 로드하세요.
        val initialData = fetchInitialData() // 데이터를 가져오는 메서드를 구현해야 합니다.

        // SharedViewModel에 데이터를 설정합니다.
        sharedViewModel.setGoalList(initialData)
    }

    private fun fetchInitialData(): List<ToDoItem> {
        // 데이터를 가져오는 로직을 추가합니다.
        return listOf() // 예시로 비어있는 리스트 반환, 실제 데이터로 교체 필요
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
