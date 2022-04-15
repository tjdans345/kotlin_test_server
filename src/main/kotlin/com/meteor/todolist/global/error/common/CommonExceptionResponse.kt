package com.meteor.todolist.global.error.common

class CommonExceptionResponse<T> (
    val code: String,
    val message: String,
    val data: T?
)