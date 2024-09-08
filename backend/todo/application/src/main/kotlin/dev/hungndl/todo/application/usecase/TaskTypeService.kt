package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Service
import kotlinx.coroutines.flow.Flow

@Service
class TaskTypeService(private val taskTypeRepository: TaskTypeRepository) {
    suspend fun createTaskType(taskType: TaskType): TaskType = taskTypeRepository.save(taskType)
    suspend fun getTaskType(id: Long): TaskType? = taskTypeRepository.findById(id)
    fun getAllTaskTypes(): Flow<TaskType> = taskTypeRepository.findAll()
    suspend fun updateTaskType(taskType: TaskType): TaskType = taskTypeRepository.update(taskType)
    suspend fun deleteTaskType(id: Long) = taskTypeRepository.delete(id)
}