package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface UpdateTaskUseCase {
    suspend fun execute(task: Task): Task
}