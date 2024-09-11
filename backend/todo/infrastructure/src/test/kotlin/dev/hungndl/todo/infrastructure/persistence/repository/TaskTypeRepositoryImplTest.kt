package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.domain.TaskType
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

class TaskTypeRepositoryImplTest {

    private val taskTypeReactiveRepository: TaskTypeReactiveRepository = mockk()
    private val taskTypeRepository = TaskTypeRepositoryImpl(taskTypeReactiveRepository)

    @Test
    fun `should find all task types`() = runBlocking {
        val taskTypeEntities = listOf(
            TaskTypeEntity(1, "Type 1"),
            TaskTypeEntity(2, "Type 2")
        )

        coEvery { taskTypeReactiveRepository.findAll() } returns Flux.fromIterable(taskTypeEntities)

        val result = taskTypeRepository.findAll().toList()

        assertEquals(2, result.size)
        assertEquals("Type 1", result[0].name)
        assertEquals("Type 2", result[1].name)
    }

    @Test
    fun `should save task type`() = runBlocking {
        val taskType = TaskType(null, "New Type")
        val savedTaskTypeEntity = TaskTypeEntity(1, "New Type")

        coEvery { taskTypeReactiveRepository.save(any()) } returns Mono.just(savedTaskTypeEntity)

        val result = taskTypeRepository.save(taskType)

        assertEquals(1, result.id)
        assertEquals("New Type", result.name)
    }

    // Add more tests for other methods (findById, update, delete, findAllById)
}