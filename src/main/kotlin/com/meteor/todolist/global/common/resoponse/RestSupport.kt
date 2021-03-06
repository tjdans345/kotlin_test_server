package com.meteor.todolist.global.common.resoponse

import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.nio.charset.StandardCharsets

val MEDIA_TYPE_APPLICATION_JSON_UTF8 = MediaType("application", "json", StandardCharsets.UTF_8)
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=utf-8"

abstract class RestSupport {

    protected open fun <T> response(data: T) : ResponseEntity<Any> {
        return ResponseEntity.ok().contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(ResponseDTO(data = data, responseEnum = ResponseEnum.EXAMPLE_ENUM2))
    }

    protected open fun unauthorized(message: String): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(message)
    }


}