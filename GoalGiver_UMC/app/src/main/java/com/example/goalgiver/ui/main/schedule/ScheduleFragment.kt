package com.example.goalgiver.ui.main.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.FragmentScheduleBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var initialToDoList: List<ToDoItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadInitialData() // 초기 데이터 로드
    }

    private fun initViews() {
        todoAdapter = ToDoAdapter()
        binding.todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }

        // 현재 년도와 월을 설정
        updateMonthYearTitle(CalendarDay.today())

        // 캘린더 설정
        binding.calendarView.setOnDateChangedListener { widget: MaterialCalendarView, date: CalendarDay, selected: Boolean ->
            val selectedDate = date.date?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
            selectedDate?.let {
                filterItemsByDate(it)
            }
        }

        // Month 변경 시, title 업데이트
        binding.calendarView.setOnMonthChangedListener { _, date ->
            updateMonthYearTitle(date)
        }

        // 달력 언어를 한국어로 설정
        binding.calendarView.setTitleFormatter(DateFormatTitleFormatter(DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.KOREAN)))
        binding.calendarView.setWeekDayLabels(arrayOf("월", "화", "수", "목", "금", "토","일"))
    }

    private fun updateMonthYearTitle(calendarDay: CalendarDay) {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.getDefault())
        binding.textMonthYear.text = calendarDay.date?.format(formatter)
    }

    private fun loadInitialData() {
        initialToDoList = listOf(
            ToDoItem("헬스장 가기", "2024.08.15", "인증"),
            ToDoItem("회의 참석", "2024.08.16", "미인증"),
            ToDoItem("친구 만나기", "2024.08.17", "완료")
        )
        todoAdapter.submitList(initialToDoList)

        // DotDecorator를 추가하여 해당 날짜에 점을 찍기
        binding.calendarView.addDecorator(DotDecorator(requireContext(), initialToDoList.map { it.date }))
    }

    private fun filterItemsByDate(selectedDate: String) {
        val filteredItems = initialToDoList.filter { it.date == selectedDate }

        if (filteredItems.isNotEmpty()) {
            todoAdapter.submitList(filteredItems)
            binding.todoRecyclerView.visibility = View.VISIBLE
            binding.placeholderLayout.visibility = View.GONE
        } else {
            todoAdapter.submitList(emptyList())
            binding.todoRecyclerView.visibility = View.GONE
            binding.placeholderLayout.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
