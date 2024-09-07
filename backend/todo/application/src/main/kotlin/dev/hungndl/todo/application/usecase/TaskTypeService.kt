package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TaskTypeService(private val taskTypeRepository: TaskTypeRepository) {
    fun createTaskType(taskType: TaskType): Mono<TaskType> = taskTypeRepository.save(taskType)
    fun getTaskType(id: Long): Mono<TaskType> = taskTypeRepository.findById(id)
    fun getAllTaskTypes(): Flux<TaskType> = taskTypeRepository.findAll()
    fun updateTaskType(taskType: TaskType): Mono<TaskType> = taskTypeRepository.update(taskType)
    fun deleteTaskType(id: Long): Mono<Void> = taskTypeRepository.delete(id)
}