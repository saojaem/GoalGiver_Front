package com.example.goalgiver.ui.main.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    private val initialToDoList: MutableList<ToDoItem> = mutableListOf()

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
        handleIncomingIntentData() // Intent 데이터 처리
        loadInitialData() // 초기 데이터 로드
    }

    private fun initViews() {
        todoAdapter = ToDoAdapter()
        binding.todoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
        updateMonthYearTitle(CalendarDay.today())
        binding.calendarView.setOnDateChangedListener { widget: MaterialCalendarView, date: CalendarDay, selected: Boolean ->
            val selectedDate = date.date?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
            selectedDate?.let { filterItemsByDate(it) }
        }
        binding.calendarView.setOnMonthChangedListener { _, date -> updateMonthYearTitle(date) }
        binding.calendarView.setTitleFormatter(DateFormatTitleFormatter(DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.KOREAN)))
        binding.calendarView.setWeekDayLabels(arrayOf("월", "화", "수", "목", "금", "토", "일"))
        binding.alram.setOnClickListener {
            val intent = Intent(requireContext(), RequestAlarmActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleIncomingIntentData() {
        arguments?.let { bundle ->
            val toDoItem = bundle.getParcelable<ToDoItem>("toDoItem")

            toDoItem?.let {
                initialToDoList.add(it)
                todoAdapter.submitList(initialToDoList.toList())
                todoAdapter.notifyDataSetChanged()

                val dates = getDatesBetween(it.startdate, it.enddate)
                binding.calendarView.addDecorator(DotDecorator(requireContext(), dates))
            }
        }
    }

    private fun loadInitialData() {
        if (initialToDoList.isEmpty()) {
            initialToDoList.addAll(
                listOf(
                    ToDoItem("🎯", "헬스장 가기", "2024.08.15", "2024.08.16", "인증"),
                    ToDoItem("🎯", "회의 참석", "2024.08.16", "2024.08.17", "인증"),
                    ToDoItem("🎯", "친구 만나기", "2024.08.17", "2024.08.20", "인증")
                )
            )
            todoAdapter.submitList(initialToDoList.toList())
            println("Initial Data added to RecyclerView: $initialToDoList")
        }
        binding.calendarView.addDecorator(DotDecorator(requireContext(), initialToDoList.map { it.startdate }))
    }

    private fun updateMonthYearTitle(calendarDay: CalendarDay) {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.getDefault())
        binding.textMonthYear.text = calendarDay.date?.format(formatter)
    }

    private fun filterItemsByDate(selectedDate: String) {
        val filteredItems = initialToDoList.filter { it.startdate <= selectedDate && it.enddate >= selectedDate }
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

    private fun getDatesBetween(startDate: String, endDate: String): List<String> {
        val dates = mutableListOf<String>()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        var start = LocalDate.parse(startDate, formatter)
        val end = LocalDate.parse(endDate, formatter)

        while (!start.isAfter(end)) {
            dates.add(start.format(formatter))
            start = start.plusDays(1)
        }
        return dates
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
