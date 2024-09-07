package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.Task
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TaskRepository {
    fun save(task: Task): Mono<Task>
    fun findById(id: Long): Mono<Task>
    fun findAll(): Flux<Task>
    fun update(task: Task): Mono<Task>
    fun delete(id: Long): Mono<Void>
}