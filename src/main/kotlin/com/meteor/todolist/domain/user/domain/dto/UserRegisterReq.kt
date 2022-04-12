package com.meteor.todolist.domain.user.domain.dto

data class UserRegisterReq(val username: String, val userEmail: String, var userPassword: String)