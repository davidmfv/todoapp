package dev.hungndl.todo.application.usecase.tasktype

import dev.hungndl.todo.domain.TaskType

interface UpdateTaskTypeUseCase {
    suspend fun execute(taskType: TaskType): TaskType
}