package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.GetTaskUseCase
import dev.hungndl.todo.domain.Task
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GetTaskUseCaseImpl(private val taskService: TaskService) : GetTaskUseCase {
    override fun execute(id: Long): Mono<Task> = taskService.getTask(id)
}