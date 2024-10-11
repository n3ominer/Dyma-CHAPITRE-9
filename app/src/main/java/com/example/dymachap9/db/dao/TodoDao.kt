package com.example.dymachap9.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dymachap9.db.entities.TodoEntity


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodos(): LiveData<List<TodoEntity>>

    @Insert
    fun addOne(todo: TodoEntity)

    @Insert
    fun addAll(todos: List<TodoEntity>)

    @Delete
    fun deleteOne(todo: TodoEntity)

    @Query("DELETE FROM todo")
    fun deleteAll()

    @Update
    fun updateOne(todo: TodoEntity)

}