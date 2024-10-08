package dev.hungndl.todo.controller

import dev.hungndl.todo.domain.Priority
import dev.hungndl.todo.domain.Status
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import java.time.LocalDate

data class TaskInput(
    val content: String,
    val priority: Priority,
    val status: Status,
    val deadline: LocalDate,
    val description: String,
    val typeId: Long
) {
    fun toTask(taskType: TaskType) = Task(
        content = content,
        priority = priority,
        status = status,
        deadline = deadline,
        description = description,
        type = taskType
    )
}