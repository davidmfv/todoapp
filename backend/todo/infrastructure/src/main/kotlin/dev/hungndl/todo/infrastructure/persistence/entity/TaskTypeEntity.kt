package dev.hungndl.todo.infrastructure.persistence.entity

import dev.hungndl.todo.domain.TaskType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("task_types")
data class TaskTypeEntity(
    @Id
    val id: Long? = null,
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