package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.domain.Task
import dev.hungndl.todo.domain.TaskType
import dev.hungndl.todo.domain.Priority  // Add this import
import dev.hungndl.todo.domain.Status    // Add this import
import dev.hungndl.todo.infrastructure.persistence.entity.TaskEntity
import dev.hungndl.todo.infrastructure.persistence.entity.TaskTypeEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

class TaskRepositoryImplTest {

    private val taskReactiveRepository: TaskReactiveRepository = mockk()
    private val taskTypeReactiveRepository: TaskTypeReactiveRepository = mockk()
    private val taskRepository = TaskRepositoryImpl(taskReactiveRepository, taskTypeReactiveRepository)

    @Test
    fun `should find all tasks`() = runBlocking {
        val taskEntities = listOf(
            TaskEntity(1, "Task 1", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", 1),
            TaskEntity(2, "Task 2", LocalDate.now(), Priority.HIGH, Status.IN_PROGRESS, "Description", 2)
        )
        val taskTypeEntities = listOf(
            TaskTypeEntity(1, "Type 1"),
            TaskTypeEntity(2, "Type 2")
        )

        coEvery { taskReactiveRepository.findAll() } returns Flux.fromIterable(taskEntities)
        coEvery { taskTypeReactiveRepository.findAllById(listOf(1L, 2L)) } returns Flux.fromIterable(taskTypeEntities)

        val result = taskRepository.findAll().toList()

        assertEquals(2, result.size)
        assertEquals("Task 1", result[0].content)
        assertEquals("Type 1", result[0].type.name)
    }

    // Add more tests for other methods (save, findById, update, delete)
    @Test
    fun `should save task`() = runBlocking {
        val taskType = TaskType(1, "Type 1")
        val task = Task(null, "New Task", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", taskType)
        val savedTaskEntity = TaskEntity(1, "New Task", LocalDate.now(), Priority.NORMAL, Status.TODO, "Description", 1)
        val savedTaskTypeEntity = TaskTypeEntity(1, "Type 1")

        coEvery { taskReactiveRepository.save(any()) } returns Mono.just(savedTaskEntity)
        coEvery { taskTypeReactiveRepository.findById(1) } returns Mono.just(savedTaskTypeEntity)

        val result = taskRepository.save(task)

        assertEquals(1, result.id)
        assertEquals("New Task", result.content)
        assertEquals("Type 1", result.type.name)
    }
}