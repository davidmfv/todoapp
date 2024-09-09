package dev.hungndl.todo.application.usecase.tasktype.impl

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.application.usecase.tasktype.GetTaskTypeUseCase
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Component

@Component
class GetTaskTypeUseCaseImpl(private val taskTypeRepository: TaskTypeRepository) : GetTaskTypeUseCase {
    override suspend fun execute(id: Long): TaskType? = taskTypeRepository.findById(id)
}