package com.example.goalgiver.ui.main.goal;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class PastDateDecorator implements DayViewDecorator {

    private final CalendarDay minDate;

    public PastDateDecorator(CalendarDay minDate) {
        this.minDate = minDate;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.isBefore(minDate); // 시작 날짜 이전 날짜만 데코레이터 적용
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.GRAY)); // 회색으로 텍스트 색상 변경
        view.setDaysDisabled(true); // 날짜 선택 비활성화
    }
}
