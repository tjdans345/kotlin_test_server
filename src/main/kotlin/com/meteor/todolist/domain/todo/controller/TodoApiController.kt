package com.meteor.todolist.domain.todo.controller

import com.meteor.todolist.domain.todo.domain.dto.response.Todo
import com.meteor.todolist.domain.todo.domain.dto.request.TodoRequest
import com.meteor.todolist.domain.todo.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/todo")
class TodoApiController(
    private val todoService: TodoService
) {


    @GetMapping
    fun getTodos(): MutableIterable<Todo> = todoService.getTodos()

    @PostMapping
    fun insertTodo(@RequestBody todoRequest: TodoRequest) = todoService.insertTodo(todoRequest.todoName)

    @PutMapping(path = ["/{todoId}"]) // PathVariable 설정
    fun updateTodo(@PathVariable("todoId") todoId: Long) = todoService.updateTodo(todoId)

    @DeleteMapping(path = ["/{todoId}"]) // PathVariable 설정
    fun deleteTodo(@PathVariable("todoId") todoId: Long) = todoService.deleteTodo(todoId)

}

