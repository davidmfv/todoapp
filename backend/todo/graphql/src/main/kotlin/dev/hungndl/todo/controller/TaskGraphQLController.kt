package dev.hungndl.todo.controller

import dev.hungndl.todo.application.service.GoalService
import dev.hungndl.todo.application.usecase.task.*
import dev.hungndl.todo.domain.Goal
import dev.hungndl.todo.domain.Task
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import kotlinx.coroutines.flow.Flow

@Controller
class TaskGraphQLController(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val goalService: GoalService
) {
    @QueryMapping
    fun tasks(): Flow<Task> = getAllTasksUseCase.execute()

    @QueryMapping
    suspend fun task(@Argument id: Long): Task? = getTaskUseCase.execute(id)

    @MutationMapping
    suspend fun createTask(@Argument input: TaskInput): Task = 
        taskTypeService.getTaskType(input.typeId)?.let { taskType ->
            createTaskUseCase.execute(input.toTask(taskType))
        } ?: throw IllegalArgumentException("Invalid task type")

    @MutationMapping
    suspend fun updateTask(@Argument id: Long, @Argument input: TaskInput): Task =
        taskTypeService.getTaskType(input.typeId)?.let { taskType ->
            updateTaskUseCase.execute(input.toTask(taskType).copy(id = id))
        } ?: throw IllegalArgumentException("Invalid task type")

    @MutationMapping
    suspend fun deleteTask(@Argument id: Long): Boolean {
        deleteTaskUseCase.execute(id)
        return true
    }

    @QueryMapping
    fun goals(): Flow<Goal> = goalService.getAllGoals()

    @MutationMapping
    suspend fun syncGoalsWithNotion(): Boolean = goalService.syncGoalsWithNotion()
}

