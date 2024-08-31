package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.infrastructure.persistence.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TaskJpaRepository : JpaRepository<TaskEntity, Long>