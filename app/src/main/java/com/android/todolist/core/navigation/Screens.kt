package com.android.todolist.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object ToDoList

@Serializable
class ToDoDetail(val toDoItemId: Int)