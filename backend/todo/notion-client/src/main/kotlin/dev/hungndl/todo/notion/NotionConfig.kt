package dev.hungndl.todo.notion

class NotionConfig(
    private val apiKey: String,
    private val apiVersion: String
) {
    fun createNotionClient(): NotionClient {
        return NotionClientImpl(apiKey, apiVersion)
    }
}