package dev.hungndl.todo.domain

import java.time.LocalDateTime

data class Task(
    val id: Long? = null,
    val content: String,
    val deadline: LocalDateTime,
    val priority: Priority,
    val status: Status,
    val description: String,
    val type: String
)
