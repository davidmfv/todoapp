package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TaskRepositoryImpl(private val taskReactiveRepository: TaskReactiveRepository) : TaskRepository {
    override fun save(task: Task): Mono<Task> = taskReactiveRepository.save(task.toEntity()).map { it.toDomain() }
    override fun findById(id: Long): Mono<Task> = taskReactiveRepository.findById(id).map { it.toDomain() }
    override fun findAll(): Flux<Task> = taskReactiveRepository.findAll().map { it.toDomain() }
    override fun update(task: Task): Mono<Task> = taskReactiveRepository.save(task.toEntity()).map { it.toDomain() }
    override fun delete(id: Long): Mono<Void> = taskReactiveRepository.deleteById(id)
}