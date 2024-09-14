package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.application.port.GoalRepository
import dev.hungndl.todo.domain.Goal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Repository

@Repository
class GoalRepositoryImpl(private val goalReactiveRepository: GoalReactiveRepository) : GoalRepository {
    override suspend fun saveAll(goals: List<Goal>): List<Goal> {
        return goalReactiveRepository.saveAll(goals).collectList().awaitFirst()
    }

    override fun findAll(): Flow<Goal> {
        return goalReactiveRepository.findAll().asFlow()
    }

    override suspend fun deleteAll() {
        goalReactiveRepository.deleteAll().awaitFirst()
    }
}