package dev.hungndl.todo.infrastructure.persistence.entity

import dev.hungndl.todo.domain.Priority
import dev.hungndl.todo.domain.Status
import dev.hungndl.todo.domain.Task
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "tasks")
class TaskEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String,
    val deadline: LocalDate,
    @Enumerated(EnumType.STRING)
    val priority: Priority,
    @Enumerated(EnumType.STRING)
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