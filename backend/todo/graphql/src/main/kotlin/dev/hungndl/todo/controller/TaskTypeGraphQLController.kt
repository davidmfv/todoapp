package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.TaskType
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import kotlinx.coroutines.flow.Flow

@Controller
class TaskTypeGraphQLController(private val taskTypeService: TaskTypeService) {

    @QueryMapping
    fun taskTypes(): Flow<TaskType> = taskTypeService.getAllTaskTypes()

    @QueryMapping
    suspend fun taskType(@Argument id: Long): TaskType? = taskTypeService.getTaskType(id)

    @MutationMapping
    suspend fun createTaskType(@Argument name: String): TaskType = 
        taskTypeService.createTaskType(TaskType(name = name))

    @MutationMapping
    suspend fun updateTaskType(@Argument id: Long, @Argument name: String): TaskType =
        taskTypeService.updateTaskType(TaskType(id = id, name = name))

    @MutationMapping
    suspend fun deleteTaskType(@Argument id: Long): Boolean {
        taskTypeService.deleteTaskType(id)
        return true
    }
}