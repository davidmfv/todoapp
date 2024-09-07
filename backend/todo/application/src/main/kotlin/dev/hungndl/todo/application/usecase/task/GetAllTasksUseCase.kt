package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface GetAllTasksUseCase {
    fun execute(): List<Task>
}