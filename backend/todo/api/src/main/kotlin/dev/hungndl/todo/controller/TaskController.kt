package dev.hungndl.todo.controller

import dev.hungndl.todo.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTasks(): Flux<Task> = taskService.getAllTasks()

    @GetMapping("/{id}")
    fun getTask(@PathVariable id: Long): Mono<Task> = taskService.getTask(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Mono<Task> = taskService.createTask(task)

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): Mono<Task> =
        taskService.updateTask(task.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long): Mono<Void> = taskService.deleteTask(id)
}