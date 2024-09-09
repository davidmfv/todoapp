package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.tasktype.*
import dev.hungndl.todo.domain.TaskType
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import kotlinx.coroutines.flow.Flow

@Controller
class TaskTypeGraphQLController(
    private val getAllTaskTypesUseCase: GetAllTaskTypesUseCase,
    private val getTaskTypeUseCase: GetTaskTypeUseCase,
    private val createTaskTypeUseCase: CreateTaskTypeUseCase,
    private val updateTaskTypeUseCase: UpdateTaskTypeUseCase,
    private val deleteTaskTypeUseCase: DeleteTaskTypeUseCase
) {

    @QueryMapping
    fun taskTypes(): Flow<TaskType> = getAllTaskTypesUseCase.execute()

    @QueryMapping
    suspend fun taskType(@Argument id: Long): TaskType? = getTaskTypeUseCase.execute(id)

    @MutationMapping
    suspend fun createTaskType(@Argument name: String): TaskType = 
        createTaskTypeUseCase.execute(TaskType(name = name))

    @MutationMapping
    suspend fun updateTaskType(@Argument id: Long, @Argument name: String): TaskType =
        updateTaskTypeUseCase.execute(TaskType(id = id, name = name))

    @MutationMapping
    suspend fun deleteTaskType(@Argument id: Long): Boolean {
        deleteTaskTypeUseCase.execute(id)
        return true
    }
}