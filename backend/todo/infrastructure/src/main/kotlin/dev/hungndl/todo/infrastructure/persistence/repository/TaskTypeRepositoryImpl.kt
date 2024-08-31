package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity

class TaskTypeRepositoryImpl(private val jpaRepository: TaskTypeJpaRepository) : TaskTypeRepository {
    override fun save(taskType: TaskType): TaskType = jpaRepository.save(taskType.toEntity()).toDomain()
    override fun findById(id: Long): TaskType? = jpaRepository.findById(id).map { it.toDomain() }.orElse(null)
    override fun findAll(): List<TaskType> = jpaRepository.findAll().map { it.toDomain() }
    override fun update(taskType: TaskType): TaskType = jpaRepository.save(taskType.toEntity()).toDomain()
    override fun delete(id: Long) = jpaRepository.deleteById(id)
}