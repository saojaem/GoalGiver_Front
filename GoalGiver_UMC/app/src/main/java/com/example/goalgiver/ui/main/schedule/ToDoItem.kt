package com.example.goalgiver.ui.main.schedule

data class ToDoItem(
    val scheduleIcon: String,
    val title: String,
    val startdate: String,
    val enddate: String,
    val status: String = "인증" // 항상 "인증" 텍스트로 설정
)