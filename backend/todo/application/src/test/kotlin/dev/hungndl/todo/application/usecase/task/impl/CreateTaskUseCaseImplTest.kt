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

class CreateTaskUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val createTaskUseCase = CreateTaskUseCaseImpl(taskRepository)

    @Test
    fun `should create task`() = runBlocking {
        val task = Task(null, "New Task", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", TaskType(1, "Type 1"))
        val createdTask = task.copy(id = 1)

        coEvery { taskRepository.save(task) } returns createdTask

        val result = createTaskUseCase.execute(task)

        assertEquals(createdTask, result)
    }
}