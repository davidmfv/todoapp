package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun createTask(task: Task): Mono<Task> = taskRepository.save(task)
    fun getTask(id: Long): Mono<Task> = taskRepository.findById(id)
    fun getAllTasks(): Flux<Task> = taskRepository.findAll()
    fun updateTask(task: Task): Mono<Task> = taskRepository.update(task)
    fun deleteTask(id: Long): Mono<Void> = taskRepository.delete(id)
}