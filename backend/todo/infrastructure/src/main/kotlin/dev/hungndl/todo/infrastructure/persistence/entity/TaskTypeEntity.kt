package dev.hungndl.todo.infrastructure.persistence.entity

import dev.hungndl.todo.domain.TaskType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "task_types")
class TaskTypeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true)
    val name: String
)

fun TaskType.toEntity() = TaskTypeEntity(
    id = id,
    name = name
)

fun TaskTypeEntity.toDomain() = TaskType(
    id = id,
    name = name
)