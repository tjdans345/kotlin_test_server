package com.meteor.todolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class TodolistApplication

fun main(args: Array<String>) {
    runApplication<TodolistApplication>(*args)
}
