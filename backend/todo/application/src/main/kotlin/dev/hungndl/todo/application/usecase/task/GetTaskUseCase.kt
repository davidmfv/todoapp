package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface GetTaskUseCase {
    suspend fun execute(id: Long): Task?
}