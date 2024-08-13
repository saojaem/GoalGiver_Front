package com.example.goalgiver.ui.main.schedule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ToDoViewModel : ViewModel() {

    // 예시 ToDo 리스트
    private val _todoList = listOf(
        ToDoModel("2024.06.19", "헬스장 가기"),
        ToDoModel("2024.06.19", "책 읽기"),
        ToDoModel("2024.06.19", "일기 쓰기"),
        ToDoModel("2024.06.20", "10000보 이상 걷기"),
        ToDoModel("2024.06.20", "파이썬 6주차 과제")
        // 추가 데이터들...
    )

    // 선택된 날짜의 ToDo 리스트를 저장할 StateFlow
    private val _filteredToDoList = MutableStateFlow<List<ToDoModel>>(emptyList())
    val filteredToDoList: StateFlow<List<ToDoModel>> = _filteredToDoList

    // 선택된 날짜에 맞게 ToDo 리스트를 필터링하는 함수
    fun filterToDoListByDate(date: String) {
        _filteredToDoList.value = _todoList.filter { it.date == date }
    }
}
