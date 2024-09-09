package dev.hungndl.todo.application.usecase.tasktype.impl

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.application.usecase.tasktype.CreateTaskTypeUseCase
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Component

@Component
class CreateTaskTypeUseCaseImpl(private val taskTypeRepository: TaskTypeRepository) : CreateTaskTypeUseCase {
    override suspend fun execute(taskType: TaskType): TaskType = taskTypeRepository.save(taskType)
}