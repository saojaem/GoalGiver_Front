package com.example.goalgiver.ui.goaldetail

import android.content.Context
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoaldetailCalendarcertificationBinding
import com.example.goalgiver.databinding.FragmentGoaldetailPhotocertificationBinding
import com.google.android.material.datepicker.DayViewDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class CalendarCertificationFragment: Fragment() {
    lateinit var binding: FragmentGoaldetailCalendarcertificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoaldetailCalendarcertificationBinding.inflate(inflater, container, false)

        binding.calendarView.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)));
        binding.calendarView.setHeaderTextAppearance(R.style.home_CalendarWidgetHeader)

        binding.calendarView.setTitleFormatter { day ->
            val inputText = day.date
            val calendarHeaderElements = inputText.toString().split("-")
            val calendarHeaderBuilder = StringBuilder()

            calendarHeaderBuilder.append(calendarHeaderElements[0]).append("년 ")
                .append(calendarHeaderElements[1]).append("월")
            calendarHeaderBuilder.toString()
        }

        binding.calendarView.setOnMonthChangedListener { widget, date ->
            binding.calendarView.removeDecorators()
            binding.calendarView.invalidateDecorators()
        }

        applyCustomStyles()

        return binding.root
    }

    private fun applyCustomStyles() {
        // 특정 날짜 설정
        val dates = listOf(
            CalendarDay.from(2024, 8, 10), // 2024년 8월 10일
            CalendarDay.from(2024, 8, 15) // 2024년 8월 15일
        )

        for (date in dates) {
            binding.calendarView.setDateSelected(date, true)
        }

        binding.calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            if (!dates.contains(date)) {
                binding.calendarView.setDateSelected(date, false)
            }
        })
    }
}