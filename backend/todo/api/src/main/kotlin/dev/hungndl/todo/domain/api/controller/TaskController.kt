package dev.hungndl.todo.domain.api.controller

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task = taskService.createTask(task)

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): Task? = taskService.getTask(id)

    @GetMapping
    fun getAllTasks(): List<Task> = taskService.getAllTasks()

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): Task =
        taskService.updateTask(task.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) = taskService.deleteTask(id)
}