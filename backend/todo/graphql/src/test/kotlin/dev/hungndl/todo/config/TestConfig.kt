package dev.hungndl.todo.config

import dev.hungndl.todo.infrastructure.persistence.repository.TaskTypeReactiveRepository
import io.mockk.mockk
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfig {
    @Bean
    fun taskTypeReactiveRepository(): TaskTypeReactiveRepository = mockk()
}