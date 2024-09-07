package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface CreateTaskUseCase {
    fun execute(task: Task): Task
}