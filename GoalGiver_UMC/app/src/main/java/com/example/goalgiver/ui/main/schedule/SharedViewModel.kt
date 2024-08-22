package com.example.goalgiver.ui.main.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _goalList = MutableLiveData<List<ToDoItem>>()
    val goalList: LiveData<List<ToDoItem>> get() = _goalList

    fun setGoalList(goals: List<ToDoItem>) {
        _goalList.value = goals
    }
    fun addGoal(toDoItem: ToDoItem) {
        val currentList = _goalList.value?.toMutableList() ?: mutableListOf()
        currentList.add(toDoItem)
        _goalList.value = currentList
    }
}