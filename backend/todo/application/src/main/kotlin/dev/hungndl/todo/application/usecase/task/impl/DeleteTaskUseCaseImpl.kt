package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.application.usecase.task.DeleteTaskUseCase
import org.springframework.stereotype.Component

@Component
class DeleteTaskUseCaseImpl(private val taskRepository: TaskRepository) : DeleteTaskUseCase {
    override suspend fun execute(id: Long) = taskRepository.delete(id)
}