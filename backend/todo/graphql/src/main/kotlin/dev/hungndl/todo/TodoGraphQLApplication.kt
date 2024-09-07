package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["dev.hungndl.todo"])
class TodoGraphQLApplication

fun main(args: Array<String>) {
    runApplication<TodoGraphQLApplication>(*args)
}