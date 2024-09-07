package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface UpdateTaskUseCase {
    fun execute(task: Task): Task
}