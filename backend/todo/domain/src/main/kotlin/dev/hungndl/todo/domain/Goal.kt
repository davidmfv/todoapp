package dev.hungndl.todo.domain

enum class GoalType {
    PROGRAMMING,
    ENGLISH
}

data class Goal(
    val id: Long? = null,
    val notionId: String,
    val name: String,
    val type: GoalType,
    val progress: Int
)