package dev.hungndl.todo.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import io.r2dbc.h2.H2ConnectionFactory
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory

@TestConfiguration
@EnableR2dbcRepositories(basePackages = ["dev.hungndl.todo.infrastructure.persistence.repository"])
class TestR2dbcConfig : AbstractR2dbcConfiguration() {

    @Bean
    @Primary
    override fun connectionFactory(): ConnectionFactory {
        return H2ConnectionFactory.inMemory("testdb")
    }

    @Bean
    @Primary
    fun r2dbcEntityTemplate(): R2dbcEntityTemplate {
        return R2dbcEntityTemplate(connectionFactory())
    }

    @Bean
    fun r2dbcRepositoryFactory(r2dbcEntityTemplate: R2dbcEntityTemplate): R2dbcRepositoryFactory {
        return R2dbcRepositoryFactory(r2dbcEntityTemplate)
    }
}