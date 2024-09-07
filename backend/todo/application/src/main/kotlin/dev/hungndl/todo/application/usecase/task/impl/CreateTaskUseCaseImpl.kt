package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.CreateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateTaskUseCaseImpl(private val taskService: TaskService) : CreateTaskUseCase {
    override fun execute(task: Task): Mono<Task> = taskService.createTask(task)
}