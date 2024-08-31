package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Service

@Service
class TaskTypeService(private val taskTypeRepository: TaskTypeRepository) {
    fun createTaskType(taskType: TaskType): TaskType = taskTypeRepository.save(taskType)
    fun getTaskType(id: Long): TaskType? = taskTypeRepository.findById(id)
    fun getAllTaskTypes(): List<TaskType> = taskTypeRepository.findAll()
    fun updateTaskType(taskType: TaskType): TaskType = taskTypeRepository.update(taskType)
    fun deleteTaskType(id: Long) = taskTypeRepository.delete(id)
}