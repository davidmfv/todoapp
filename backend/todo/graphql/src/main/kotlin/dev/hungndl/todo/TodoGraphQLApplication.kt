package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoGraphQLApplication

fun main(args: Array<String>) {
    runApplication<TodoGraphQLApplication>(*args)
}