package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.TaskType

interface TaskTypeRepository {
    fun save(taskType: TaskType): TaskType
    fun findById(id: Long): TaskType?
    fun findAll(): List<TaskType>
    fun update(taskType: TaskType): TaskType
    fun delete(id: Long)
}