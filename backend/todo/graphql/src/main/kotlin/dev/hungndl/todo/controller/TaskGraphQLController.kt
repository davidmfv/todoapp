package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.domain.Task
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class TaskGraphQLController(private val taskService: TaskService) {

    @QueryMapping
    fun tasks(): List<Task> = taskService.getAllTasks()

    @QueryMapping
    fun task(@Argument id: Long): Task? = taskService.getTask(id)

    @MutationMapping
    fun createTask(@Argument input: TaskInput): Task = taskService.createTask(input.toTask())

    @MutationMapping
    fun updateTask(@Argument id: Long, @Argument input: TaskInput): Task =
        taskService.updateTask(input.toTask().copy(id = id))

    @MutationMapping
    fun deleteTask(@Argument id: Long): Boolean = taskService.deleteTask(id).let { true }
}

