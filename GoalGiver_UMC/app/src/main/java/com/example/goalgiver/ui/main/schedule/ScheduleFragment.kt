package com.example.goalgiver.ui.main.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.goalgiver.databinding.FragmentScheduleBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ScheduleFragment: Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()

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
        observeViewModel()
    }

    private fun initViews() {
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            val selectedDate = "${date.year}.${"%02d".format(date.month)}.${"%02d".format(date.day)}"
            viewModel.filterToDoListByDate(selectedDate)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.filteredToDoList.collect { todoList ->
                val todoText = todoList.joinToString(separator = "\n") { it.title }
                binding.todoTextView.text = if (todoText.isEmpty()) "할 일이 없습니다." else todoText
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
