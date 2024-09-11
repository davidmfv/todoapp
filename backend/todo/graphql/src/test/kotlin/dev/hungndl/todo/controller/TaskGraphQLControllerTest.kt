package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.task.*
import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.domain.Priority  // Add this import
import dev.hungndl.todo.domain.Status    // Add this import
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.graphql.test.tester.GraphQlTester
import java.time.LocalDate
import dev.hungndl.todo.config.TestR2dbcConfig
import dev.hungndl.todo.config.TestConfig

@GraphQlTest(TaskGraphQLController::class)
class TaskGraphQLControllerTest {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockBean
    private lateinit var getAllTasksUseCase: GetAllTasksUseCase

    @MockBean
    private lateinit var getTaskUseCase: GetTaskUseCase

    @MockBean
    private lateinit var createTaskUseCase: CreateTaskUseCase

    @MockBean
    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @MockBean
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @MockBean
    private lateinit var taskTypeService: TaskTypeService

    private val sampleTask = Task(1, "Sample Task", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", TaskType(1, "Type 1"))
    private val sampleTaskInput = TaskInput("Sample Task", Priority.NORMAL, Status.TODO, LocalDate.now(), "Description", 1)

    @Test
    fun `should return all tasks`() {
        val tasks = listOf(
            sampleTask,
            Task(2, "Task 2", LocalDate.now(), Priority.HIGH, Status.IN_PROGRESS, "Description", TaskType(2, "Type 2"))
        )

        coEvery { getAllTasksUseCase.execute() } returns flowOf(*tasks.toTypedArray())

        val query = """
            query {
                tasks {
                    id
                    content
                    priority
                    status
                    deadline
                    description
                    type {
                        id
                        name
                    }
                }
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .path("tasks")
            .entityList(Task::class.java)
            .hasSize(2)
    }

    @Test
    fun `should return a single task`() {
        coEvery { getTaskUseCase.execute(1) } returns sampleTask

        val query = """
            query {
                task(id: 1) {
                    id
                    content
                    priority
                    status
                    deadline
                    description
                    type {
                        id
                        name
                    }
                }
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .path("task")
            .entity(Task::class.java)
            .isEqualTo(sampleTask)
    }

    @Test
    fun `should create a task`() {
        coEvery { taskTypeService.getTaskType(1) } returns TaskType(1, "Type 1")
        coEvery { createTaskUseCase.execute(any()) } returns sampleTask

        val mutation = """
            mutation {
                createTask(input: {
                    content: "Sample Task"
                    priority: NORMAL
                    status: TODO
                    deadline: "${LocalDate.now()}"
                    description: "Description"
                    typeId: 1
                }) {
                    id
                    content
                    priority
                    status
                    deadline
                    description
                    type {
                        id
                        name
                    }
                }
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("createTask")
            .entity(Task::class.java)
            .isEqualTo(sampleTask)
    }

    @Test
    fun `should update a task`() {
        coEvery { taskTypeService.getTaskType(1) } returns TaskType(1, "Type 1")
        coEvery { updateTaskUseCase.execute(any()) } returns sampleTask

        val mutation = """
            mutation {
                updateTask(id: 1, input: {
                    content: "Updated Task"
                    priority: HIGH
                    status: IN_PROGRESS
                    deadline: "${LocalDate.now()}"
                    description: "Updated Description"
                    typeId: 1
                }) {
                    id
                    content
                    priority
                    status
                    deadline
                    description
                    type {
                        id
                        name
                    }
                }
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("updateTask")
            .entity(Task::class.java)
            .isEqualTo(sampleTask)
    }

    @Test
    fun `should delete a task`() {
        coEvery { deleteTaskUseCase.execute(1) } returns Unit

        val mutation = """
            mutation {
                deleteTask(id: 1)
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("deleteTask")
            .entity(Boolean::class.java)
            .isEqualTo(true)
    }
}