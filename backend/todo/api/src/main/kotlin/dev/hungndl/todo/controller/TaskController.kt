package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.task.*
import dev.hungndl.todo.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlinx.coroutines.flow.Flow

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) {

    @GetMapping
    fun getAllTasks(): Flow<Task> = getAllTasksUseCase.execute()

    @GetMapping("/{id}")
    suspend fun getTask(@PathVariable id: Long): Task? = getTaskUseCase.execute(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createTask(@RequestBody task: Task): Task = createTaskUseCase.execute(task)

    @PutMapping("/{id}")
    suspend fun updateTask(@PathVariable id: Long, @RequestBody task: Task): Task =
        updateTaskUseCase.execute(task.copy(id = id))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun deleteTask(@PathVariable id: Long) = deleteTaskUseCase.execute(id)
}