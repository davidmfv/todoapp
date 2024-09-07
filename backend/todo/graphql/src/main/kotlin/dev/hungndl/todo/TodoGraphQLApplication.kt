package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["dev.hungndl.todo"])
class TodoGraphQLApplication

fun main(args: Array<String>) {
    runApplication<TodoGraphQLApplication>(*args)
}