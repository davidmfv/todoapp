package dev.hungndl.todo.domain

import java.time.LocalDate

data class Task(
    val id: Long? = null,
    val content: String = "",
    val deadline: LocalDate = LocalDate.now(),
    val priority: Priority = Priority.NORMAL,
    val status: Status = Status.TODO,
    val description: String = "",
    val type: TaskType
)
