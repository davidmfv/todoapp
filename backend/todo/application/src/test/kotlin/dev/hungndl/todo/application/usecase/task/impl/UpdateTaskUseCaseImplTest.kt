package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.domain.Priority  // Add this import
import dev.hungndl.todo.domain.Status    // Add this import
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class UpdateTaskUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val updateTaskUseCase = UpdateTaskUseCaseImpl(taskRepository)

    @Test
    fun `should update task`() = runBlocking {
        val task = Task(1, "Updated Task", LocalDate.now(), Priority.HIGH, Status.IN_PROGRESS, "Updated Description", TaskType(1, "Type 1"))

        coEvery { taskRepository.update(task) } returns task

        val result = updateTaskUseCase.execute(task)

        assertEquals(task, result)
    }
}