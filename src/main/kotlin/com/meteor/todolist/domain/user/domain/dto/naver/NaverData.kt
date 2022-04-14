package com.meteor.todolist.domain.user.domain.dto.naver


import com.google.gson.annotations.SerializedName
import com.meteor.todolist.domain.user.domain.dto.SocialCommon

data class NaverData(
    @SerializedName("message")
    val message: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("resultcode")
    val resultcode: String
) : SocialCommon {
    override fun getEmail() {
        TODO("Not yet implemented")
    }

    override fun getName() {
        TODO("Not yet implemented")
    }
}