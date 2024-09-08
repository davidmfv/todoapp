package dev.hungndl.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.reactive.config.EnableWebFlux
import org.slf4j.LoggerFactory
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger

@SpringBootApplication
@ComponentScan(basePackages = ["dev.hungndl.todo"])
@EnableWebFlux
class TodoGraphQLApplication

fun main(args: Array<String>) {
    // Set log levels programmatically
    (LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger).level = Level.INFO
    (LoggerFactory.getLogger("io.r2dbc.mysql") as Logger).level = Level.DEBUG
    (LoggerFactory.getLogger("org.springframework.data.r2dbc") as Logger).level = Level.DEBUG
    (LoggerFactory.getLogger("io.r2dbc.mysql.QUERY") as Logger).level = Level.DEBUG
    (LoggerFactory.getLogger("io.r2dbc.mysql.PARAM") as Logger).level = Level.DEBUG

    runApplication<TodoGraphQLApplication>(*args)
}