package com.example.dymachap9.repositories

import androidx.lifecycle.LiveData
import com.example.dymachap9.db.dao.TodoDao
import com.example.dymachap9.db.entities.TodoEntity

class TodoRepository(private val todoDao: TodoDao) {

    fun getAll(): LiveData<List<TodoEntity>> {
        return this.todoDao.getAllTodos()
    }

    fun insert(todo: TodoEntity) {
        this.todoDao.addOne(todo)
    }

    fun insertAll(todos: List<TodoEntity>) {
        this.todoDao.addAll(todos)
    }

    fun delete(todo: TodoEntity) {
        this.todoDao.deleteOne(todo)
    }

    fun deleteAll() {
        this.todoDao.deleteAll()
    }

    fun update(todo: TodoEntity) {
        this.todoDao.updateOne(todo)
    }
}