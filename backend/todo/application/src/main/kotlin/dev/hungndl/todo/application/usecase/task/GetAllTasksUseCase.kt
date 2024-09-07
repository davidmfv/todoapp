package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task
import reactor.core.publisher.Flux

interface GetAllTasksUseCase {
    fun execute(): Flux<Task>
}