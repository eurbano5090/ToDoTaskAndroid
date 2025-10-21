package com.android.todolist.data.repositories

import com.android.todolist.data.dao.ToDoDao
import com.android.todolist.data.entity.toDomain
import com.android.todolist.data.entity.toEntity
import com.android.todolist.data.models.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map


class ToDoRepository(private val toDoDao: ToDoDao) {

    fun obtenerToDos(): Flow<List<ToDoItem>> =
        toDoDao.obtenerToDos().map { entities -> entities.map { it.toDomain() }
        }

    suspend fun obtenerPorId(id: String): ToDoItem? =
        toDoDao.obtenerPorId(id)?.toDomain()


    suspend fun insertarToDo(toDo: ToDoItem) {
        toDoDao.insertarToDo(toDo.toEntity())
    }

    suspend fun actualizarToDo(toDo: ToDoItem) {
        toDoDao.actualizarToDo(toDo.id, toDo.title, toDo.description, toDo.date, toDo.time)
    }

    suspend fun toggleDone(id: String, estado: Boolean) {
        toDoDao.actualizarEstado(id, estado)
    }


    suspend fun eliminarToDo(id: String) {
        toDoDao.eliminarToDo(id)
    }
}
