package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task
import reactor.core.publisher.Mono

interface CreateTaskUseCase {
    fun execute(task: Task): Mono<Task>
}