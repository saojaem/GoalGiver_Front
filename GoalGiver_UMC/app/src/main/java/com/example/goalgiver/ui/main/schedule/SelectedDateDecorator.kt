package com.example.goalgiver.ui.main.schedule

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.goalgiver.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class SelectedDateDecorator(private val context: Context, private val selectedDate: CalendarDay) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == selectedDate
    }

    override fun decorate(view: DayViewFacade) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.selected_date_background)
        view.setBackgroundDrawable(drawable!!)
    }
}