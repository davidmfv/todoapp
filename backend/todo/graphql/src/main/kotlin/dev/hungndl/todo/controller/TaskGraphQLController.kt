package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.task.*
import dev.hungndl.todo.domain.Task
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class TaskGraphQLController(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) {

    @QueryMapping
    fun tasks(): Flux<Task> = getAllTasksUseCase.execute()

    @QueryMapping
    fun task(@Argument id: Long): Mono<Task> = getTaskUseCase.execute(id)

    @MutationMapping
    fun createTask(@Argument input: TaskInput): Mono<Task> = createTaskUseCase.execute(input.toTask())

    @MutationMapping
    fun updateTask(@Argument id: Long, @Argument input: TaskInput): Mono<Task> =
        updateTaskUseCase.execute(input.toTask().copy(id = id))

    @MutationMapping
    fun deleteTask(@Argument id: Long): Mono<Boolean> = deleteTaskUseCase.execute(id).thenReturn(true)
}

