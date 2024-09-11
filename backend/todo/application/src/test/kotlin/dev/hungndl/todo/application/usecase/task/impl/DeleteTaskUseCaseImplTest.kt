package dev.hungndl.todo.application.usecase.task.impl

import dev.hungndl.todo.application.port.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class DeleteTaskUseCaseImplTest {

    private val taskRepository: TaskRepository = mockk()
    private val deleteTaskUseCase = DeleteTaskUseCaseImpl(taskRepository)

    @Test
    fun `should delete task`() = runBlocking {
        val taskId = 1L

        coEvery { taskRepository.delete(taskId) } returns Unit

        deleteTaskUseCase.execute(taskId)

        coVerify(exactly = 1) { taskRepository.delete(taskId) }
    }
}