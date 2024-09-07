package dev.hungndl.todo.application.usecase.task

import reactor.core.publisher.Mono

interface DeleteTaskUseCase {
    fun execute(id: Long): Mono<Void>
}