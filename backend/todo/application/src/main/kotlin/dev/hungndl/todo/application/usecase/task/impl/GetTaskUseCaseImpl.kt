package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.GetTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class GetTaskUseCaseImpl(private val taskService: TaskService) : GetTaskUseCase {
    override suspend fun execute(id: Long): Task? = taskService.getTask(id)
}