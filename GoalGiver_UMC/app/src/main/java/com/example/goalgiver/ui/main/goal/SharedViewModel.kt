package com.example.goalgiver.ui.main.goal
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goalgiver.ui.main.schedule.ToDoItem

class SharedViewModel : ViewModel() {
    private val _toDoItems = MutableLiveData<List<ToDoItem>>()
    val toDoItems: LiveData<List<ToDoItem>> get() = _toDoItems

    fun addToDoItem(toDoItem: ToDoItem) {
        val updatedList = _toDoItems.value?.toMutableList() ?: mutableListOf()
        updatedList.add(toDoItem)
        _toDoItems.value = updatedList
    }
}