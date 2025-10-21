package com.android.todolist.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.android.todolist.data.models.ToDoItem


@Entity(tableName = "toDos")
data class ToDoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val time: String,
    val description: String? = null,
    val date: String,
    val categoriaId: String,
    val isDone: Boolean = false
   ) {
}

fun ToDoEntity.toDomain(): ToDoItem = ToDoItem(
    id = id,
    title = title,
    time = time,
    description = description,
    date = date,
    categoriaId = categoriaId,
    isDone = isDone
)

fun ToDoItem.toEntity(): ToDoEntity = ToDoEntity(
    id = id,
    title = title,
    time = time,
    description = description,
    date = date,
    categoriaId = categoriaId,
    isDone = isDone
)
