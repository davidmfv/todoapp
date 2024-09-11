package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@ComponentScan(basePackages = ["dev.hungndl.todo"])
@EnableWebFlux
class TodoGraphQLApplication

fun main(args: Array<String>) {
    runApplication<TodoGraphQLApplication>(*args)
}