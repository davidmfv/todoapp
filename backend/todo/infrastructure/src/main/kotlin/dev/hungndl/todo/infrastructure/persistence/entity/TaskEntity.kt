package dev.hungndl.todo.infrastructure.persistence.entity

import dev.hungndl.todo.domain.Priority
import dev.hungndl.todo.domain.Status
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
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
    @Column("task_type_id")
    val taskTypeId: Long
)

fun Task.toEntity() = TaskEntity(
    id = id,
    content = content,
    deadline = deadline,
    priority = priority,
    status = status,
    description = description,
    taskTypeId = type.id ?: throw IllegalArgumentException("TaskType must have an id")
)

fun TaskEntity.toDomain(taskType: TaskType) = Task(
    id = id,
    content = content,
    deadline = deadline,
    priority = priority,
    status = status,
    description = description,
    type = taskType
)