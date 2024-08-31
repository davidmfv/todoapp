package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.infrastructure.persistence.entity.toDomain
import dev.hungndl.todo.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository

@Repository
class TaskRepositoryImpl(private val jpaRepository: TaskJpaRepository) : TaskRepository {
    override fun save(task: Task): Task = jpaRepository.save(task.toEntity()).toDomain()
    override fun findById(id: Long): Task? = jpaRepository.findById(id).map { it.toDomain() }.orElse(null)
    override fun findAll(): List<Task> = jpaRepository.findAll().map { it.toDomain() }
    override fun update(task: Task): Task = jpaRepository.save(task.toEntity()).toDomain()
    override fun delete(id: Long) = jpaRepository.deleteById(id)
}