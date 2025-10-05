package com.android.todolist.data.models
import kotlinx.serialization.Serializable
@Serializable
data class ToDoItem (
    val id: String,
    val title: String,
    val description: String? = null,
    val date: String,
    val isDone: Boolean = false
)