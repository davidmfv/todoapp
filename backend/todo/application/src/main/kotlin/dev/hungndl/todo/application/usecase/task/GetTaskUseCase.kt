package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task
import reactor.core.publisher.Mono

interface GetTaskUseCase {
    fun execute(id: Long): Mono<Task>
}