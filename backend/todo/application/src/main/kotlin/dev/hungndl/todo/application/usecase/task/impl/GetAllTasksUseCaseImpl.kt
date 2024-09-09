package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.application.usecase.task.GetAllTasksUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component
import kotlinx.coroutines.flow.Flow

@Component
class GetAllTasksUseCaseImpl(private val taskRepository: TaskRepository) : GetAllTasksUseCase {
    override fun execute(): Flow<Task> = taskRepository.findAll()
}