package dev.hungndl.todo.application.usecase.tasktype.impl

import dev.hungndl.todo.application.port.TaskTypeRepository
import dev.hungndl.todo.application.usecase.tasktype.GetAllTaskTypesUseCase
import dev.hungndl.todo.domain.TaskType
import org.springframework.stereotype.Component
import kotlinx.coroutines.flow.Flow

@Component
class GetAllTaskTypesUseCaseImpl(private val taskTypeRepository: TaskTypeRepository) : GetAllTaskTypesUseCase {
    override fun execute(): Flow<TaskType> = taskTypeRepository.findAll()
}