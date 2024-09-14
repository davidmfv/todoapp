package dev.hungndl.todo.application.service

import dev.hungndl.todo.application.port.GoalRepository
import dev.hungndl.todo.domain.Goal
import dev.hungndl.todo.domain.GoalType
import dev.hungndl.todo.notion.NotionClient
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class GoalService(
    private val goalRepository: GoalRepository,
    private val notionClient: NotionClient
) {
    fun getAllGoals(): Flow<Goal> = goalRepository.findAll()

    suspend fun syncGoalsWithNotion(): Boolean {
        val notionGoals = notionClient.queryDatabase("your-notion-database-id-here")
        val goals = notionGoals.mapNotNull { notionPage ->
            try {
                Goal(
                    notionId = notionPage.id,
                    name = extractName(notionPage.properties),
                    type = extractType(notionPage.properties),
                    progress = extractProgress(notionPage.properties)
                )
            } catch (e: Exception) {
                // Log the error and skip this goal
                null
            }
        }
        goalRepository.deleteAll()
        goalRepository.saveAll(goals)
        return true
    }

    private fun extractName(properties: Map<String, Any>): String {
        return ""
//        return (properties["Name"] as? Map<*, *>)
//            ?.get("title") as? List<Map<String, Any>>
//            ?.firstOrNull()
//            ?.get("plain_text") as? String
//            ?: throw IllegalArgumentException("Invalid or missing Name property")
    }

    private fun extractType(properties: Map<String, Any>): GoalType {
//        val typeString = (properties["Type"] as? Map<*, *>)
//            ?.get("select") as? Map<String, Any>
//            ?.get("name") as? String
//            ?: throw IllegalArgumentException("Invalid or missing Type property")
//        return GoalType.valueOf(typeString.uppercase())
        return GoalType.PROGRAMMING
    }

    private fun extractProgress(properties: Map<String, Any>): Int {
        return (properties["Progress"] as? Map<*, *>)
            ?.get("number") as? Int
            ?: throw IllegalArgumentException("Invalid or missing Progress property")
    }
}