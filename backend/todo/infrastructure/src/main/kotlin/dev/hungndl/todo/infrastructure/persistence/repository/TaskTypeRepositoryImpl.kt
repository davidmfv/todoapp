package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.infrastructure.persistence.entity.TaskTypeEntity
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle

@Repository
class TaskTypeRepositoryImpl(private val taskTypeReactiveRepository: TaskTypeReactiveRepository) : TaskTypeRepository {
    override suspend fun save(taskType: TaskType): TaskType =
        taskTypeReactiveRepository.save(taskType.toEntity()).awaitSingle().toDomain()

    override suspend fun findById(id: Long): TaskType? =
        taskTypeReactiveRepository.findById(id).awaitFirstOrNull()?.toDomain()

    override fun findAll(): Flow<TaskType> =
        taskTypeReactiveRepository.findAll().asFlow().map { it.toDomain() }

    override suspend fun update(taskType: TaskType): TaskType =
        taskTypeReactiveRepository.save(taskType.toEntity()).awaitSingle().toDomain()

    override suspend fun delete(id: Long) {
        taskTypeReactiveRepository.deleteById(id).awaitFirstOrNull()
    }
}