package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.application.usecase.task.CreateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class CreateTaskUseCaseImpl(private val taskRepository: TaskRepository) : CreateTaskUseCase {
    override fun execute(task: Task): Task = taskRepository.save(task)
}