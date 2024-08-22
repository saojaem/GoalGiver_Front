package com.example.goalgiver.ui.main.schedule

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.goalgiver.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DotDecorator(context: Context, dates: List<String>) : DayViewDecorator {

    private val calendarDayList = dates.map { date ->
        CalendarDay.from(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd")))
    }

    private val dotColor = ContextCompat.getColor(context, R.color.brand_blue)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return calendarDayList.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(8f, dotColor))
    }
}
