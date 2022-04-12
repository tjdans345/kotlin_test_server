package com.meteor.todolist.global.common.resoponse

import java.time.LocalDateTime

data class ResponseDTO<T> (
    // TODO: ENUM 추가 해보기
    val data: T,
    val message: String
)