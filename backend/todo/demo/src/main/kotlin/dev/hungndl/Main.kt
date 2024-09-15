@file:Suppress("UNCHECKED_CAST")

package dev.hungndl

import dev.hungndl.todo.notion.NotionClient
import dev.hungndl.todo.notion.NotionConfig
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking {
    val logger = LoggerFactory.getLogger("NotionDemo")

    val apiKey = ""
    val apiVersion = "2022-06-28"
    val databaseId = ""

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
        PropertyConverterFactory.getConverter(type).convert(value)
    }
}


fun interface PropertyConverter {
    fun convert(value: Map<String, Any>): Any
}

object RichTextConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val text = value["rich_text"] as List<Map<String, Any>>
        return text.joinToString("") { it["plain_text"] as String }
    }
}

object UniqueIdConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val items = value["unique_id"] as Map<String, Any>
        return (items["number"] as Double).toInt()
    }
}

object StatusConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val items = value["status"] as Map<String, Any>
        return items["name"] as String
    }
}

object DateConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val items = value["date"] as Map<String, Any>
        return items["start"] as String
    }
}

object TitleConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val text = value["title"] as List<Map<String, Any>>
        return text.joinToString("") { it["plain_text"] as String }
    }
}

object PeopleConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        val text = value["people"] as List<Map<String, Any>>
        return text.firstNotNullOfOrNull { it["id"] as String } ?: ""
    }
}

object DefaultConverter : PropertyConverter {
    override fun convert(value: Map<String, Any>): Any {
        return ""
    }
}

object PropertyConverterFactory {
    fun getConverter(type: String): PropertyConverter {
        return when (type) {
            "rich_text" -> RichTextConverter
            "unique_id" -> UniqueIdConverter
            "status" -> StatusConverter
            "date" -> DateConverter
            "title" -> TitleConverter
            "people" -> PeopleConverter
            else -> DefaultConverter
        }
    }
}
