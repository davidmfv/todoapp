package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.Task

interface TaskRepository {
    fun save(task: Task): Task
    fun findById(id: Long): Task?
    fun findAll(): List<Task>
    fun update(task: Task): Task
    fun delete(id: Long)
}