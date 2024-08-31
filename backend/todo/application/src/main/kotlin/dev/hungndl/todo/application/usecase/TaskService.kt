package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun createTask(task: Task): Task = taskRepository.save(task)
    fun getTask(id: Long): Task? = taskRepository.findById(id)
    fun getAllTasks(): List<Task> = taskRepository.findAll()
    fun updateTask(task: Task): Task = taskRepository.update(task)
    fun deleteTask(id: Long) = taskRepository.delete(id)
}