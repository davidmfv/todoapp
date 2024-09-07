package dev.hungndl.todo.application.usecase.task

import dev.hungndl.todo.domain.Task
import reactor.core.publisher.Mono

interface UpdateTaskUseCase {
    fun execute(task: Task): Mono<Task>
}