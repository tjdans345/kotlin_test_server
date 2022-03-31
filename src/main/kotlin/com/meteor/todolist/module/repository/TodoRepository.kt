package com.meteor.todolist.module.repository

import com.meteor.todolist.module.domain.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, Long> {

    fun findAllByOrderByIdDesc() : MutableList<Todo>




}