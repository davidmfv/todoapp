package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.GetAllTasksUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component

@Component
class GetAllTasksUseCaseImpl(private val taskService: TaskService) : GetAllTasksUseCase {
    override suspend fun execute(): List<Task> = taskService.getAllTasks()
}