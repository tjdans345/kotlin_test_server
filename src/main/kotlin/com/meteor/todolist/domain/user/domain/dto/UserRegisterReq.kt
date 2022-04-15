package com.meteor.todolist.domain.user.domain.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class UserRegisterReq(

    @field:NotEmpty
    val username: String?,

    val userEmail: String,

    var userPassword: String
    )