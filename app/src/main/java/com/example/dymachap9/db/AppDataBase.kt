package com.example.dymachap9.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dymachap9.db.dao.TodoDao
import com.example.dymachap9.db.entities.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

}