package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class TaskRepositoryImpl(
    private val taskReactiveRepository: TaskReactiveRepository,
    private val taskTypeReactiveRepository: TaskTypeReactiveRepository
) : TaskRepository {
    override fun save(task: Task): Mono<Task> = 
        taskReactiveRepository.save(task.toEntity())
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.typeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }

    override fun findById(id: Long): Mono<Task> = 
        taskReactiveRepository.findById(id)
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.typeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }

    override fun findAll(): Flux<Task> = 
        taskReactiveRepository.findAll()
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.typeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }

    override fun update(task: Task): Mono<Task> = 
        taskReactiveRepository.save(task.toEntity())
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.typeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }

    override fun delete(id: Long): Mono<Void> = taskReactiveRepository.deleteById(id)
}