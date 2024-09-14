package dev.hungndl.todo.configuration

import dev.hungndl.todo.notion.NotionClient
import dev.hungndl.todo.notion.NotionConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotionConfiguration {

    @Value("\${notion.api.key}")
    private lateinit var apiKey: String

    @Value("\${notion.api.version}")
    private lateinit var apiVersion: String

    @Bean
    fun notionClient(): NotionClient {
        val notionConfig = NotionConfig(apiKey, apiVersion)
        return notionConfig.createNotionClient()
    }
}