package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.UpdateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class UpdateTaskUseCaseImpl(private val taskService: TaskService) : UpdateTaskUseCase {
    override suspend fun execute(task: Task): Task = taskService.updateTask(task)
}