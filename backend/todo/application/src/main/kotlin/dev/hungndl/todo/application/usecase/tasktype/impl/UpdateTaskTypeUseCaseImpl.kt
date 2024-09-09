package dev.hungndl.todo.application.usecase.tasktype.impl

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.application.usecase.tasktype.UpdateTaskTypeUseCase
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Component

@Component
class UpdateTaskTypeUseCaseImpl(private val taskTypeRepository: TaskTypeRepository) : UpdateTaskTypeUseCase {
    override suspend fun execute(taskType: TaskType): TaskType = taskTypeRepository.update(taskType)
}