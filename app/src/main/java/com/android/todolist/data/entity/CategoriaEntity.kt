package com.android.todolist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey val id: String,
    val nombre: String,
    val iconRes: Int,
)

