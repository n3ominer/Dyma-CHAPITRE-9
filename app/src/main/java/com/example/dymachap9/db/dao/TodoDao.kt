package com.example.dymachap9.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dymachap9.db.entities.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodos(): List<TodoEntity>

    @Insert
    fun addAll(users: List<TodoEntity>)

    @Delete
    fun delete(user: TodoEntity)
}