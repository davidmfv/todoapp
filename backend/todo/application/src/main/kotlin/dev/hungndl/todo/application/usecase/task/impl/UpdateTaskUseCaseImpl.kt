package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.application.usecase.task.UpdateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class UpdateTaskUseCaseImpl(private val taskRepository: TaskRepository) : UpdateTaskUseCase {
    override fun execute(task: Task): Task = taskRepository.update(task)
}