package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.application.usecase.task.GetTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class GetTaskUseCaseImpl(private val taskRepository: TaskRepository) : GetTaskUseCase {
    override fun execute(id: Long): Task? = taskRepository.findById(id)
}