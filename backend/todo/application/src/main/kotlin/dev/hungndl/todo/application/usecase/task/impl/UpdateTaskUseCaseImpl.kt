package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.UpdateTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UpdateTaskUseCaseImpl(private val taskService: TaskService) : UpdateTaskUseCase {
    override fun execute(task: Task): Mono<Task> = taskService.updateTask(task)
}