package com.android.todolist.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.todolist.data.models.ToDoItem

class ToDoViewModel : ViewModel() {

    private val _toDoList = MutableLiveData<List<ToDoItem>>(emptyList())
    val toDoList: LiveData<List<ToDoItem>> = _toDoList

    fun addToDo(todo: ToDoItem) {
        _toDoList.value = _toDoList.value.orEmpty() + todo
    }

    fun updateToDo(updated: ToDoItem) {
        _toDoList.value = _toDoList.value.orEmpty().map { if(it.id == updated.id) updated else it }
    }

    fun removeToDo(id: String) {
        _toDoList.value = _toDoList.value.orEmpty().filter { it.id != id }
    }

    fun toggleDone(id: String) {
        _toDoList.value = _toDoList.value.orEmpty().map {
            if(it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }

    fun getToDoById(id: String): ToDoItem? {
        return _toDoList.value?.find { it.id == id }
    }
}
