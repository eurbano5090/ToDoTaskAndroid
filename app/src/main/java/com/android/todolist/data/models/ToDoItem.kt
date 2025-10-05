package com.android.todolist.data.models


data class ToDoItem (
    val id: String,
    val title: String,
    val description: String? = null,
    val date: String,
    val isDone: Boolean = false
)