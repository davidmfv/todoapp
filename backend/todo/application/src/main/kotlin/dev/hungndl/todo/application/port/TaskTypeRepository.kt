package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.TaskType
import kotlinx.coroutines.flow.Flow

interface TaskTypeRepository {
    suspend fun save(taskType: TaskType): TaskType
    suspend fun findById(id: Long): TaskType?
    fun findAll(): Flow<TaskType>
    fun findAllById(ids: List<Long>): Flow<TaskType>
    suspend fun update(taskType: TaskType): TaskType
    suspend fun delete(id: Long)
}