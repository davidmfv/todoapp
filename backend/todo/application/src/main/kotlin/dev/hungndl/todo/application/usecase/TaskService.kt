package dev.hungndl.todo.application.usecase

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

@Service
class TaskService(private val taskRepository: TaskRepository) {
    suspend fun createTask(task: Task): Task = taskRepository.save(task)
    suspend fun getTask(id: Long): Task? = taskRepository.findById(id)
    suspend fun getAllTasks(): List<Task> = taskRepository.findAll().toList()
    suspend fun updateTask(task: Task): Task = taskRepository.update(task)
    suspend fun deleteTask(id: Long) = taskRepository.delete(id)
}