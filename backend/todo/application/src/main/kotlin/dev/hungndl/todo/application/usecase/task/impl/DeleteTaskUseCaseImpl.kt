package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.DeleteTaskUseCase
import org.springframework.stereotype.Component

@Component
class DeleteTaskUseCaseImpl(private val taskService: TaskService) : DeleteTaskUseCase {
    override suspend fun execute(id: Long) = taskService.deleteTask(id)
}