package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun save(task: Task): Task
    suspend fun findById(id: Long): Task?
    fun findAll(): Flow<Task>
    suspend fun update(task: Task): Task
    suspend fun delete(id: Long)
}