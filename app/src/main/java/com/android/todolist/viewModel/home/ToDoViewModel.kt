package com.android.todolist.viewModel.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.android.todolist.data.models.ToDoItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class ToDoViewModel : ViewModel()  {

    private val _toDoList = mutableStateListOf<ToDoItem>()
    val toDoList: List<ToDoItem> = _toDoList

    val pendingToDos: List<ToDoItem>
        get() = _toDoList.filter { !it.isDone }

    val doneToDos: List<ToDoItem>
        get() = _toDoList.filter { it.isDone }

    fun saveToDos(context: Context) {
        val prefs = context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
        val json = Json.encodeToString(_toDoList.toList())
        prefs.edit { putString("todos", json) }
    }

    fun loadToDos(context: Context) {
        val prefs = context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
        val json = prefs.getString("todos", "[]") ?: "[]"
        _toDoList.clear()
        _toDoList.addAll(Json.decodeFromString<List<ToDoItem>>(json))
    }
    fun addToDo(todo: ToDoItem) {
        _toDoList.add(todo)
    }

    fun updateToDo(updated: ToDoItem) {
        _toDoList.replaceAll { if (it.id == updated.id) updated else it }
    }

    fun removeToDo(id: String) {
        _toDoList.removeIf { it.id == id }
    }

    fun toggleDone(id: String) {
        _toDoList.replaceAll { if (it.id == id) it.copy(isDone = !it.isDone) else it }
    }


    fun findById(id: String): ToDoItem? {
            return _toDoList.find { it.id == id }
    }


}
