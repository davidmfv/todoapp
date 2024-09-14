package dev.hungndl.todo.application.port

import dev.hungndl.todo.domain.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    suspend fun saveAll(goals: List<Goal>): List<Goal>
    fun findAll(): Flow<Goal>
    suspend fun deleteAll()
}