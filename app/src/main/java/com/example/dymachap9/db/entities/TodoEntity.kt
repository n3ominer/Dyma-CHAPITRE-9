package com.example.dymachap9.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "todo_title") val data: String,
    @ColumnInfo(name = "todo_description") val description: String,
    @ColumnInfo(name = "todo_is_finished") val isFinished: Boolean
)
