package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task

interface GetTaskUseCase {
    fun execute(id: Long): Task?
}