package com.android.todolist.data.models

object ToDoData {
    val toDoList = mutableListOf(
        ToDoItem(
            id = "1",
            title = "Comprar frutas",
            description = "Ir al supermercado a comprar manzanas y plátanos",
            date = "2025-10-05",
            isDone = false
        ),
        ToDoItem(
            id = "2",
            title = "Revisar correo",
            description = "Responder los mensajes pendientes del trabajo",
            date = "2025-10-05",
            isDone = true
        ),
        ToDoItem(
            id = "3",
            title = "Hacer ejercicio",
            description = "Correr 30 minutos por la mañana",
            date = "2025-10-06",
            isDone = false
        )
    )
    fun ToDoData.findById(id: String): ToDoItem? {
        return toDoList.find { it.id == id.toString() } }

    }
