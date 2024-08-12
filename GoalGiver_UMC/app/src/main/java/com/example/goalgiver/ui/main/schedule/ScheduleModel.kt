package com.example.goalgiver.ui.main.schedule

data class ScheduleModel(
    val id: Long,                   // 스케줄의 고유 ID
    val title: String,              // 스케줄 제목
    val description: String?,       // 스케줄 설명 (선택적)
    val startDate: String?,         // 스케줄 시작 날짜 (yyyy.MM.dd HH:mm 형식의 문자열)
    val endDate: String?,           // 스케줄 종료 날짜 (yyyy.MM.dd HH:mm 형식의 문자열)
    val isAllDay: Boolean = false   // 종일 이벤트 여부
)