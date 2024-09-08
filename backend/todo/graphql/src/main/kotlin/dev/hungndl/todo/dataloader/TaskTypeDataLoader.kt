package dev.hungndl.todo.dataloader

import dev.hungndl.todo.application.usecase.TaskTypeService
import dev.hungndl.todo.domain.TaskType
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

@Component
class TaskTypeDataLoader(private val taskTypeService: TaskTypeService) {
    fun createDataLoader(): DataLoader<Long, TaskType?> {
        return DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                runBlocking {
                    val taskTypesMap = taskTypeService.getTaskTypesByIds(ids)
                    ids.map { id -> taskTypesMap[id] }
                }
            }
        }
    }
}