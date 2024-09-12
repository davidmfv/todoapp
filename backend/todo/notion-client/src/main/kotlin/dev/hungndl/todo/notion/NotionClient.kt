package dev.hungndl.todo.notion

interface NotionClient {
    suspend fun getPage(pageId: String): NotionPage
    suspend fun createPage(parentId: String, properties: Map<String, Any>): NotionPage
    suspend fun updatePage(pageId: String, properties: Map<String, Any>): NotionPage
    suspend fun getDatabase(databaseId: String): NotionDatabase
    suspend fun queryDatabase(databaseId: String, filter: Map<String, Any>? = null, sorts: List<Map<String, Any>>? = null): List<NotionPage>
    suspend fun createDatabase(parentId: String, title: String, properties: Map<String, Any>): NotionDatabase
    suspend fun updateDatabase(databaseId: String, properties: Map<String, Any>): NotionDatabase
}