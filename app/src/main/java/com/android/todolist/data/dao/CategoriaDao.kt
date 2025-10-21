package com.android.todolist.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.android.todolist.data.entity.Categoria

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM categorias WHERE id = :id")
    suspend fun obtenerPorId(id: String): Categoria?
}