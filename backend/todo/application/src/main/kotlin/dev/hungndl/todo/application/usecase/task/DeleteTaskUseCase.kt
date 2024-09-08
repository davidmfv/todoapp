package dev.hungndl.todo.application.usecase.task

interface DeleteTaskUseCase {
    suspend fun execute(id: Long)
}