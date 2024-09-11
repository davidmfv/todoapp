package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.domain.Priority  // Add this import
import dev.hungndl.todo.domain.Status    // Add this import
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GetAllTasksUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val getAllTasksUseCase = GetAllTasksUseCaseImpl(taskRepository)

    @Test
    fun `should return all tasks`() = runBlocking {
        val tasks = listOf(
            Task(1, "Task 1", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", TaskType(1, "Type 1")),
            Task(2, "Task 2", LocalDate.now(), Priority.HIGH, Status.IN_PROGRESS, "Description", TaskType(2, "Type 2"))
        )

        coEvery { taskRepository.findAll() } returns flowOf(*tasks.toTypedArray())

        val result = getAllTasksUseCase.execute().toList()

        assertEquals(tasks, result)
    }
}