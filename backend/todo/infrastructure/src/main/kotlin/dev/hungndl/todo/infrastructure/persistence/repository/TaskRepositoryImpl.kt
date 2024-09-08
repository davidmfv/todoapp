package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle

@Repository
class TaskRepositoryImpl(
    private val taskReactiveRepository: TaskReactiveRepository,
    private val taskTypeReactiveRepository: TaskTypeReactiveRepository
) : TaskRepository {
    override suspend fun save(task: Task): Task = 
        taskReactiveRepository.save(task.toEntity())
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.taskTypeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }.awaitSingle()

    override suspend fun findById(id: Long): Task? = 
        taskReactiveRepository.findById(id)
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.taskTypeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }.awaitFirstOrNull()

    override fun findAll(): Flow<Task> = 
        taskReactiveRepository.findAll()
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.taskTypeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }.asFlow()

    override suspend fun update(task: Task): Task = 
        taskReactiveRepository.save(task.toEntity())
            .flatMap { taskEntity ->
                taskTypeReactiveRepository.findById(taskEntity.taskTypeId)
                    .map { taskTypeEntity -> taskEntity.toDomain(taskTypeEntity.toDomain()) }
            }.awaitSingle()

    override suspend fun delete(id: Long) {
        taskReactiveRepository.deleteById(id).awaitFirstOrNull()
    }
}