package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.TaskType
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TaskTypeRepository {
    fun save(taskType: TaskType): Mono<TaskType>
    fun findById(id: Long): Mono<TaskType>
    fun findAll(): Flux<TaskType>
    fun update(taskType: TaskType): Mono<TaskType>
    fun delete(id: Long): Mono<Void>
}