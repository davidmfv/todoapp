package dev.hungndl.todo.application.usecase.tasktype

import dev.hungndl.todo.domain.TaskType

interface GetTaskTypeUseCase {
    suspend fun execute(id: Long): TaskType?
}