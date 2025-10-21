package com.android.todolist.data.repositories

import com.android.todolist.data.dao.CategoriaDao
import com.android.todolist.data.entity.Categoria



class CategoriaRepository (private val categoriaDao:  CategoriaDao) {

    suspend fun findById(id: String): Categoria? =
        categoriaDao.obtenerPorId(id)
}