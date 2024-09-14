package dev.hungndl

import dev.hungndl.todo.notion.NotionClient
import dev.hungndl.todo.notion.NotionConfig
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking {
    val logger = LoggerFactory.getLogger("NotionDemo")

    val apiKey = "secret_z0xXcu1eakaIvs5qRiqsA3styHLJRm4WBASBrU8g3U4"
    val apiVersion = "2022-06-28"
    val databaseId = "101da8c9c7fb8099a9c7c90cba6c7dff"

    val notionConfig = NotionConfig(apiKey, apiVersion)
    val notionClient: NotionClient = notionConfig.createNotionClient()

    try {
        logger.info("Fetching database from Notion...")
        val database = notionClient.getDatabase(databaseId)
        logger.info("Successfully fetched database: ${database.title}")

        logger.info("Querying database contents...")
        val pages = notionClient.queryDatabase(databaseId)
        logger.info("Found ${pages.size} pages in the database")

        pages.forEach { page ->
            @Suppress("UNCHECKED_CAST") val data = convertToMap(page.properties as Map<String, Map<String, Any>>)
            logger.info("Page: ${page.url} - id: ${data["Title"] as String}")
        }
    } catch (e: Exception) {
        logger.error("Error occurred while fetching Notion data", e)
    }
}

@Suppress("UNCHECKED_CAST")
private fun convertToMap(properties: Map<String, Map<String, Any>>): Map<String, Any> {
    return properties.mapValues { (_, value) ->
        val type = value["type"] as String
        when (type) {
            "rich_text" -> {
                val text = value["rich_text"] as List<Map<String, Any>>
                text.joinToString("") { it["plain_text"] as String }
            }
            "unique_id" -> {
                val items = value["unique_id"] as Map<String, Any>
                (items["number"] as Double).toInt()
            }
            "status" -> {
                val items = value["status"] as Map<String, Any>
                items["name"] as String
            }
            "date" -> {
                val items = value["date"] as Map<String, Any>
                items["start"] as String
            }
            "title" -> {
                val text = value["title"] as List<Map<String, Any>>
                text.joinToString("") { it["plain_text"] as String }
            }
            "people" -> {
                val text = value["people"] as List<Map<String, Any>>
                text.firstNotNullOfOrNull { it["id"] as String } ?: ""
            }
            else -> ""
        }
    }
}
