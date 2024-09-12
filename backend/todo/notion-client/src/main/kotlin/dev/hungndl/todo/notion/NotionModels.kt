package dev.hungndl.todo.notion

data class NotionPage(
    val id: String,
    val properties: Map<String, Any>,
    val url: String
)

data class NotionDatabase(
    val id: String,
    val title: List<Map<String, Any>>,
    val properties: Map<String, Any>,
    val url: String
)