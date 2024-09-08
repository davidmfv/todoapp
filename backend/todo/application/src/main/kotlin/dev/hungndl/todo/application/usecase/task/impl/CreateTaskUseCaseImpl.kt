package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.CreateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class CreateTaskUseCaseImpl(private val taskService: TaskService) : CreateTaskUseCase {
    override suspend fun execute(task: Task): Task = taskService.createTask(task)
}