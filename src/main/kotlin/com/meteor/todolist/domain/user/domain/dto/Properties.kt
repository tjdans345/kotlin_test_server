package com.meteor.todolist.domain.user.domain.dto
import com.fasterxml.jackson.annotation.JsonProperty

data class Properties(
    @JsonProperty("custom_field1")
    val customField1: String,
    @JsonProperty("custom_field2")
    val customField2: String,
    @JsonProperty("nickname")
    val nickname: String,
    @JsonProperty("profile_image")
    val profileImage: String,
    @JsonProperty("thumbnail_image")
    val thumbnailImage: String
)