package com.example.dymachap9.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.dymachap9.db.AppDataBase
import com.example.dymachap9.db.entities.TodoEntity
import com.example.dymachap9.models.TodoModel
import com.example.dymachap9.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val context: Context): ViewModel() {

    private lateinit var todoRepo: TodoRepository
    lateinit var todos: LiveData<List<TodoEntity>>

    lateinit var appDb: AppDataBase

    init {
        createAppDb() // Create DB
        val todoDao = appDb.todoDao()
        todoRepo = TodoRepository(todoDao)
        todos = todoRepo.getAll()
    }

    private fun createAppDb() {
        appDb = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "todo-app-db"
        ).build()
    }

    fun populateDataBase(todos: List<TodoModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            this@TodoViewModel.todoRepo.insertAll(
                todos.map {
                    it.mapModelToEntity()
                }
            )
        }
    }

    fun clearDb() {
        CoroutineScope(Dispatchers.IO).launch {
            this@TodoViewModel.todoRepo.deleteAll()
        }
    }

}