package com.meteor.todolist.domain.user.domain.dto.kakao
import com.fasterxml.jackson.annotation.JsonProperty

data class KaKaoData(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount,
    @JsonProperty("properties")
    val properties: Properties
)