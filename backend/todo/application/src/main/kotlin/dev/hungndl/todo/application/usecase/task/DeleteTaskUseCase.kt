package dev.hungndl.todo.application.usecase.task

interface DeleteTaskUseCase {
    fun execute(id: Long)
}