package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TaskTypeRepositoryImpl(private val taskTypeReactiveRepository: TaskTypeReactiveRepository) : TaskTypeRepository {
    override fun save(taskType: TaskType): Mono<TaskType> = taskTypeReactiveRepository.save(taskType.toEntity()).map { it.toDomain() }
    override fun findById(id: Long): Mono<TaskType> = taskTypeReactiveRepository.findById(id).map { it.toDomain() }
    override fun findAll(): Flux<TaskType> = taskTypeReactiveRepository.findAll().map { it.toDomain() }
    override fun update(taskType: TaskType): Mono<TaskType> = taskTypeReactiveRepository.save(taskType.toEntity()).map { it.toDomain() }
    override fun delete(id: Long): Mono<Void> = taskTypeReactiveRepository.deleteById(id)
}