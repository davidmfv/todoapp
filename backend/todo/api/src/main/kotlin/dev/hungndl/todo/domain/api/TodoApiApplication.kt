package dev.hungndl.todo.domain.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoApiApplication

fun main(args: Array<String>) {
    runApplication<TodoApiApplication>(*args)
}