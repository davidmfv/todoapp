package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.infrastructure.persistence.entity.TaskTypeEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskTypeReactiveRepository : ReactiveCrudRepository<TaskTypeEntity, Long>