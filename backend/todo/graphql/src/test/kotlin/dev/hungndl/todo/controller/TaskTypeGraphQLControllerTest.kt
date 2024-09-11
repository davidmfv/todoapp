package dev.hungndl.todo.controller

import dev.hungndl.todo.application.usecase.tasktype.*
import dev.hungndl.todo.domain.TaskType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.graphql.test.tester.GraphQlTester

@GraphQlTest(TaskTypeGraphQLController::class)
class TaskTypeGraphQLControllerTest {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockBean
    private lateinit var getAllTaskTypesUseCase: GetAllTaskTypesUseCase

    @MockBean
    private lateinit var getTaskTypeUseCase: GetTaskTypeUseCase

    @MockBean
    private lateinit var createTaskTypeUseCase: CreateTaskTypeUseCase

    @MockBean
    private lateinit var updateTaskTypeUseCase: UpdateTaskTypeUseCase

    @MockBean
    private lateinit var deleteTaskTypeUseCase: DeleteTaskTypeUseCase

    private val sampleTaskType = TaskType(1, "Sample Type")

    @Test
    fun `should return all task types`() {
        val taskTypes = listOf(
            sampleTaskType,
            TaskType(2, "Type 2")
        )

        coEvery { getAllTaskTypesUseCase.execute() } returns flowOf(*taskTypes.toTypedArray())

        val query = """
            query {
                taskTypes {
                    id
                    name
                }
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .path("taskTypes")
            .entityList(TaskType::class.java)
            .hasSize(2)
    }

    @Test
    fun `should return a single task type`() {
        coEvery { getTaskTypeUseCase.execute(1) } returns sampleTaskType

        val query = """
            query {
                taskType(id: 1) {
                    id
                    name
                }
            }
        """.trimIndent()

        graphQlTester.document(query)
            .execute()
            .path("taskType")
            .entity(TaskType::class.java)
            .isEqualTo(sampleTaskType)
    }

    @Test
    fun `should create a task type`() {
        coEvery { createTaskTypeUseCase.execute(any()) } returns sampleTaskType

        val mutation = """
            mutation {
                createTaskType(name: "Sample Type") {
                    id
                    name
                }
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("createTaskType")
            .entity(TaskType::class.java)
            .isEqualTo(sampleTaskType)
    }

    @Test
    fun `should update a task type`() {
        coEvery { updateTaskTypeUseCase.execute(any()) } returns sampleTaskType

        val mutation = """
            mutation {
                updateTaskType(id: 1, name: "Updated Type") {
                    id
                    name
                }
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("updateTaskType")
            .entity(TaskType::class.java)
            .isEqualTo(sampleTaskType)
    }

    @Test
    fun `should delete a task type`() {
        coEvery { deleteTaskTypeUseCase.execute(1) } returns Unit

        val mutation = """
            mutation {
                deleteTaskType(id: 1)
            }
        """.trimIndent()

        graphQlTester.document(mutation)
            .execute()
            .path("deleteTaskType")
            .entity(Boolean::class.java)
            .isEqualTo(true)
    }
}