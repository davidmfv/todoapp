package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.GetAllTasksUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component
import kotlinx.coroutines.flow.Flow

@Component
class GetAllTasksUseCaseImpl(private val taskService: TaskService) : GetAllTasksUseCase {
    override fun execute(): Flow<Task> = taskService.getAllTasks()
}