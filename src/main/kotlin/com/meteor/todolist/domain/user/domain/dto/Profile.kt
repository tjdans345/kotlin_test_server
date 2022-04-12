package com.meteor.todolist.domain.user.domain.dto
import com.fasterxml.jackson.annotation.JsonProperty

data class Profile(
    @JsonProperty("is_default_image")
    val isDefaultImage: Boolean,
    @JsonProperty("nickname")
    val nickname: String,
    @JsonProperty("profile_image_url")
    val profileImageUrl: String,
    @JsonProperty("thumbnail_image_url")
    val thumbnailImageUrl: String
)