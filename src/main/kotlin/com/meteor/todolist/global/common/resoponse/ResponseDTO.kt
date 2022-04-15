package com.meteor.todolist.global.common.resoponse

import com.fasterxml.jackson.annotation.JsonIgnore

data class ResponseDTO<T> (
    @JsonIgnore
    val responseEnum: ResponseEnum,
    val data: T?,
    ) {

    val code: Int = responseEnum.code
    val message: String = responseEnum.message


}