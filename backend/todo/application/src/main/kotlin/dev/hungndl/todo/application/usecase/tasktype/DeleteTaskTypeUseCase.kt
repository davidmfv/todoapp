package dev.hungndl.todo.application.usecase.tasktype

interface DeleteTaskTypeUseCase {
    suspend fun execute(id: Long)
}