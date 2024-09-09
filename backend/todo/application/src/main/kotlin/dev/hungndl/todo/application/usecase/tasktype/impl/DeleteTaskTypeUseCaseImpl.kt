package dev.hungndl.todo.application.usecase.tasktype.impl

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.application.usecase.tasktype.DeleteTaskTypeUseCase
import org.springframework.stereotype.Component

@Component
class DeleteTaskTypeUseCaseImpl(private val taskTypeRepository: TaskTypeRepository) : DeleteTaskTypeUseCase {
    override suspend fun execute(id: Long) = taskTypeRepository.delete(id)
}