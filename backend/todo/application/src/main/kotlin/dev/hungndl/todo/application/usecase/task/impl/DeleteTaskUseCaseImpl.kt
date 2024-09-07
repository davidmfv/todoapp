package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.usecase.TaskService
import dev.hungndl.todo.application.usecase.task.DeleteTaskUseCase
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class DeleteTaskUseCaseImpl(private val taskService: TaskService) : DeleteTaskUseCase {
    override fun execute(id: Long): Mono<Void> = taskService.deleteTask(id)
}