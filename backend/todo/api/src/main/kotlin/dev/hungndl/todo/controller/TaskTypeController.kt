package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.tasktype.*
import dev.hungndl.todo.domain.TaskType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlinx.coroutines.flow.Flow

@RestController
@RequestMapping("/api/task-types")
class TaskTypeController(
    private val getAllTaskTypesUseCase: GetAllTaskTypesUseCase,
    private val getTaskTypeUseCase: GetTaskTypeUseCase,
    private val createTaskTypeUseCase: CreateTaskTypeUseCase,
    private val updateTaskTypeUseCase: UpdateTaskTypeUseCase,
    private val deleteTaskTypeUseCase: DeleteTaskTypeUseCase
) {

    @GetMapping
    fun getAllTaskTypes(): Flow<TaskType> = getAllTaskTypesUseCase.execute()

    @GetMapping("/{id}")
    suspend fun getTaskType(@PathVariable id: Long): TaskType? = getTaskTypeUseCase.execute(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createTaskType(@RequestBody taskType: TaskType): TaskType = createTaskTypeUseCase.execute(taskType)

    @PutMapping("/{id}")
    suspend fun updateTaskType(@PathVariable id: Long, @RequestBody taskType: TaskType): TaskType =
        updateTaskTypeUseCase.execute(taskType.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun deleteTaskType(@PathVariable id: Long) = deleteTaskTypeUseCase.execute(id)
}