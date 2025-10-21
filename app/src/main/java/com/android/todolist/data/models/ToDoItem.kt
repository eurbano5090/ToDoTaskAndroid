package com.android.todolist.data.models
import kotlinx.serialization.Serializable

@Serializable
data class ToDoItem (
    val id: String,
    val title: String,
    val time: String,
    val description: String? = null,
    val date: String,
    val categoriaId: String,
    val isDone: Boolean = false
)