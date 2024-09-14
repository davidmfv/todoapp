package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.domain.Goal
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalReactiveRepository : ReactiveCrudRepository<Goal, Long>