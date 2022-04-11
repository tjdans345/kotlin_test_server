package com.meteor.todolist.domain.todo.repository

import com.meteor.todolist.domain.todo.domain.dto.response.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, Long> {

    fun findAllByOrderByIdDesc() : MutableList<Todo>




}