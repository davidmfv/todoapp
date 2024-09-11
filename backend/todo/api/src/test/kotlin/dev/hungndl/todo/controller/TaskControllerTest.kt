package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.task.*
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.domain.Priority  // Add this import
import dev.hungndl.todo.domain.Status    // Add this import
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate

class TaskControllerTest {

    private lateinit var webTestClient: WebTestClient
    private lateinit var getAllTasksUseCase: GetAllTasksUseCase
    private lateinit var getTaskUseCase: GetTaskUseCase
    private lateinit var createTaskUseCase: CreateTaskUseCase
    private lateinit var updateTaskUseCase: UpdateTaskUseCase
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @BeforeEach
    fun setup() {
        getAllTasksUseCase = mockk()
        getTaskUseCase = mockk()
        createTaskUseCase = mockk()
        updateTaskUseCase = mockk()
        deleteTaskUseCase = mockk()

        val taskController = TaskController(
            getAllTasksUseCase,
            getTaskUseCase,
            createTaskUseCase,
            updateTaskUseCase,
            deleteTaskUseCase
        )

        webTestClient = WebTestClient.bindToController(taskController).build()
    }

    @Test
    fun `should return all tasks`() = runBlocking {
        val tasks = listOf(
            Task(1, "Task 1", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", TaskType(1, "Type 1")),
            Task(2, "Task 2", LocalDate.now(), Priority.HIGH, Status.IN_PROGRESS, "Description", TaskType(2, "Type 2"))
        )

        coEvery { getAllTasksUseCase.execute() } returns flowOf(*tasks.toTypedArray())

        webTestClient.get().uri("/api/tasks")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Task::class.java)
            .hasSize(2)
    }

    // Add more tests for other endpoints (getTask, createTask, updateTask, deleteTask)
}