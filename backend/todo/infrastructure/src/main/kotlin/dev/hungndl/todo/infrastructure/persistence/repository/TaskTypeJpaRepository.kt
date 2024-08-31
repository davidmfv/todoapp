package dev.hungndl.todo.infrastructure.persistence.repository

import dev.hungndl.todo.infrastructure.persistence.entity.TaskTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskTypeJpaRepository: JpaRepository<TaskTypeEntity, Long>