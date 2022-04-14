package com.meteor.todolist.domain.user.domain.dto.naver


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)