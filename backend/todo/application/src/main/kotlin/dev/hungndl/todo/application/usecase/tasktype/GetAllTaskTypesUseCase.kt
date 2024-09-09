package dev.hungndl.todo.application.usecase.tasktype

import dev.hungndl.todo.domain.TaskType
import kotlinx.coroutines.flow.Flow

interface GetAllTaskTypesUseCase {
    fun execute(): Flow<TaskType>
}