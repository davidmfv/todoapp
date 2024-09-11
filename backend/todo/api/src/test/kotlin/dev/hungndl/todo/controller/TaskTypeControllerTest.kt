package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.tasktype.*
import dev.hungndl.todo.domain.TaskType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient

class TaskTypeControllerTest {

    private lateinit var webTestClient: WebTestClient
    private lateinit var getAllTaskTypesUseCase: GetAllTaskTypesUseCase
    private lateinit var getTaskTypeUseCase: GetTaskTypeUseCase
    private lateinit var createTaskTypeUseCase: CreateTaskTypeUseCase
    private lateinit var updateTaskTypeUseCase: UpdateTaskTypeUseCase
    private lateinit var deleteTaskTypeUseCase: DeleteTaskTypeUseCase

    @BeforeEach
    fun setup() {
        getAllTaskTypesUseCase = mockk()
        getTaskTypeUseCase = mockk()
        createTaskTypeUseCase = mockk()
        updateTaskTypeUseCase = mockk()
        deleteTaskTypeUseCase = mockk()

        val taskTypeController = TaskTypeController(
            getAllTaskTypesUseCase,
            getTaskTypeUseCase,
            createTaskTypeUseCase,
            updateTaskTypeUseCase,
            deleteTaskTypeUseCase
        )

        webTestClient = WebTestClient.bindToController(taskTypeController).build()
    }

    @Test
    fun `should return all task types`() = runBlocking {
        val taskTypes = listOf(
            TaskType(1, "Type 1"),
            TaskType(2, "Type 2")
        )

        coEvery { getAllTaskTypesUseCase.execute() } returns flowOf(*taskTypes.toTypedArray())

        webTestClient.get().uri("/api/task-types")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(TaskType::class.java)
            .hasSize(2)
    }

    // Add more tests for other endpoints (getTaskType, createTaskType, updateTaskType, deleteTaskType)
}