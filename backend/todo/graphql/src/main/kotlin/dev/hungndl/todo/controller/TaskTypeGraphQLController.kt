package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.TaskType
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class TaskTypeGraphQLController(private val taskTypeService: TaskTypeService) {

    @QueryMapping
    fun taskTypes(): Flux<TaskType> = taskTypeService.getAllTaskTypes()

    @QueryMapping
    fun taskType(@Argument id: Long): Mono<TaskType> = taskTypeService.getTaskType(id)

    @MutationMapping
    fun createTaskType(@Argument input: TaskTypeInput): Mono<TaskType> = 
        taskTypeService.createTaskType(input.toTaskType())

    @MutationMapping
    fun updateTaskType(@Argument id: Long, @Argument input: TaskTypeInput): Mono<TaskType> =
        taskTypeService.updateTaskType(input.toTaskType().copy(id = id))

    @MutationMapping
    fun deleteTaskType(@Argument id: Long): Mono<Boolean> = 
        taskTypeService.deleteTaskType(id).thenReturn(true)
}

data class TaskTypeInput(val name: String) {
    fun toTaskType() = TaskType(name = name)
}