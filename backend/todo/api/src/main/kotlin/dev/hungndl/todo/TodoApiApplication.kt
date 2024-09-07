package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class TodoApiApplication

fun main(args: Array<String>) {
    runApplication<TodoApiApplication>(*args)
}