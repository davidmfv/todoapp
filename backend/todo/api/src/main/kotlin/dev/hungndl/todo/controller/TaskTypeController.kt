package dev.hungndl.todo.domain.api.controller

import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.TaskType
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
@RequestMapping("/api/task-types")
class TaskTypeController(private val taskTypeService: TaskTypeService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTaskType(@RequestBody taskType: TaskType): TaskType = taskTypeService.createTaskType(taskType)

    @GetMapping("/{id}")
    fun getTaskType(@PathVariable id: Long): TaskType? = taskTypeService.getTaskType(id)

    @GetMapping
    fun getAllTaskTypes(): List<TaskType> = taskTypeService.getAllTaskTypes()

    @PutMapping("/{id}")
    fun updateTaskType(@PathVariable id: Long, @RequestBody taskType: TaskType): TaskType =
        taskTypeService.updateTaskType(taskType.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTaskType(@PathVariable id: Long) = taskTypeService.deleteTaskType(id)
}