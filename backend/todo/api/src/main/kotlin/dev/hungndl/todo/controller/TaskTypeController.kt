package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.TaskType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/task-types")
class TaskTypeController(private val taskTypeService: TaskTypeService) {

    @GetMapping
    fun getAllTaskTypes(): Flux<TaskType> = taskTypeService.getAllTaskTypes()

    @GetMapping("/{id}")
    fun getTaskType(@PathVariable id: Long): Mono<TaskType> = taskTypeService.getTaskType(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTaskType(@RequestBody taskType: TaskType): Mono<TaskType> = taskTypeService.createTaskType(taskType)

    @PutMapping("/{id}")
    fun updateTaskType(@PathVariable id: Long, @RequestBody taskType: TaskType): Mono<TaskType> =
        taskTypeService.updateTaskType(taskType.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTaskType(@PathVariable id: Long): Mono<Void> = taskTypeService.deleteTaskType(id)
}