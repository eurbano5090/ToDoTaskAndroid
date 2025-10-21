package com.android.todolist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.todolist.data.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarToDo(toDo: ToDoEntity)

    @Query("SELECT * FROM toDos ORDER BY date, time")
    fun obtenerToDos(): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM toDos WHERE id = :id")
    suspend fun obtenerPorId(id: String): ToDoEntity?

    @Query("UPDATE toDos SET isDone = :estado WHERE id = :id")
    suspend fun actualizarEstado(id: String, estado: Boolean)

    @Query("UPDATE toDos SET title = :title, description = :description, date = :date, time = :time WHERE id = :id")
    suspend fun actualizarToDo(id: String, title: String, description: String?, date: String, time: String)

    @Query("DELETE FROM toDos WHERE id = :id")
    suspend fun eliminarToDo(id: String)
}

