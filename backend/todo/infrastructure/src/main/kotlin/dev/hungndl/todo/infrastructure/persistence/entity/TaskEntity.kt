package dev.hungndl.todo.infrastructure.persistence.entity

import dev.hungndl.todo.domain.Priority
import dev.hungndl.todo.domain.Status
import dev.hungndl.todo.domain.Task
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("tasks")
data class TaskEntity(
    @Id
    val id: Long? = null,
    val content: String,
    val deadline: LocalDate,
    val priority: Priority,
    val status: Status,
    val description: String,
    val type: String
)

fun Task.toEntity() = TaskEntity(
    id = id,
    content = content,
    deadline = deadline,
    priority = priority,
    status = status,
    description = description,
    type = type
)

fun TaskEntity.toDomain() = Task(
    id = id,
    content = content,
    deadline = deadline,
    priority = priority,
    status = status,
    description = description,
    type = type
)