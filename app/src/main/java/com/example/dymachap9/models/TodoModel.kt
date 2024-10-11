package com.example.dymachap9.models

import com.example.dymachap9.db.entities.TodoEntity


data class TodoModel (
    val data: String,
    val description: String,
    val isFinished: Boolean
) {

    fun mapModelToEntity(): TodoEntity {
        return TodoEntity(
            data = this.data,
            description = this.description,
            isFinished = this.isFinished
        )
    }

    fun mapFromEntity(entity: TodoEntity): TodoModel {
        return TodoModel(
            entity.data,
            entity.description,
            entity.isFinished
        )
    }
}
