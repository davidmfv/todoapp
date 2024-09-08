package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTasksUseCase {
    suspend fun execute(): Flow<Task>
}