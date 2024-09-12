package dev.hungndl.todo.notion

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor  // Add this import
import java.io.IOException

class NotionClientImpl(
    private val apiKey: String,
    private val apiVersion: String,
    private val baseUrl: String = "https://api.notion.com/v1"
) : NotionClient {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private suspend fun executeRequest(request: Request): String = withContext(Dispatchers.IO) {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            response.body?.string() ?: throw IOException("Empty response body")
        }
    }

    private fun createRequest(
        method: String,
        path: String,
        body: RequestBody? = null
    ): Request = Request.Builder()
        .url("$baseUrl$path")
        .header("Authorization", "Bearer $apiKey")
        .header("Notion-Version", apiVersion)
        .header("Content-Type", "application/json")
        .method(method, body)
        .build()

    override suspend fun getPage(pageId: String): NotionPage {
        val request = createRequest("GET", "/pages/$pageId")
        val response = executeRequest(request)
        return moshi.adapter(NotionPage::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionPage")
    }

    override suspend fun createPage(parentId: String, properties: Map<String, Any>): NotionPage {
        val body = mapOf(
            "parent" to mapOf("database_id" to parentId),
            "properties" to properties
        )
        val jsonBody = moshi.adapter(Map::class.java).toJson(body)
        val request = createRequest("POST", "/pages", jsonBody.toRequestBody("application/json".toMediaType()))
        val response = executeRequest(request)
        return moshi.adapter(NotionPage::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionPage")
    }

    // Implement other methods similarly...

    override suspend fun queryDatabase(databaseId: String, filter: Map<String, Any>?, sorts: List<Map<String, Any>>?): List<NotionPage> {
        val body = mutableMapOf<String, Any>()
        filter?.let { body["filter"] = it }
        sorts?.let { body["sorts"] = it }
        val jsonBody = moshi.adapter(Map::class.java).toJson(body)
        val request = createRequest("POST", "/databases/$databaseId/query", jsonBody.toRequestBody("application/json".toMediaType()))
        val response = executeRequest(request)
        val queryResponse = moshi.adapter(QueryDatabaseResponse::class.java).fromJson(response)
            ?: throw IOException("Failed to parse QueryDatabaseResponse")
        return queryResponse.results
    }

    // Implement remaining methods...

    override suspend fun updatePage(pageId: String, properties: Map<String, Any>): NotionPage {
        val body = mapOf("properties" to properties)
        val jsonBody = moshi.adapter(Map::class.java).toJson(body)
        val request = createRequest("PATCH", "/pages/$pageId", jsonBody.toRequestBody("application/json".toMediaType()))
        val response = executeRequest(request)
        return moshi.adapter(NotionPage::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionPage")
    }

    override suspend fun getDatabase(databaseId: String): NotionDatabase {
        val request = createRequest("GET", "/databases/$databaseId")
        val response = executeRequest(request)
        return moshi.adapter(NotionDatabase::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionDatabase")
    }

    override suspend fun createDatabase(parentId: String, title: String, properties: Map<String, Any>): NotionDatabase {
        val body = mapOf(
            "parent" to mapOf("page_id" to parentId),
            "title" to listOf(mapOf("text" to mapOf("content" to title))),
            "properties" to properties
        )
        val jsonBody = moshi.adapter(Map::class.java).toJson(body)
        val request = createRequest("POST", "/databases", jsonBody.toRequestBody("application/json".toMediaType()))
        val response = executeRequest(request)
        return moshi.adapter(NotionDatabase::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionDatabase")
    }

    override suspend fun updateDatabase(databaseId: String, properties: Map<String, Any>): NotionDatabase {
        val body = mapOf("properties" to properties)
        val jsonBody = moshi.adapter(Map::class.java).toJson(body)
        val request = createRequest("PATCH", "/databases/$databaseId", jsonBody.toRequestBody("application/json".toMediaType()))
        val response = executeRequest(request)
        return moshi.adapter(NotionDatabase::class.java).fromJson(response)
            ?: throw IOException("Failed to parse NotionDatabase")
    }

    // Implement remaining methods...
}

private data class QueryDatabaseResponse(val results: List<NotionPage>)