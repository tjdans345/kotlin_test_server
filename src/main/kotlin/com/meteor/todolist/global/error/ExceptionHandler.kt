package com.meteor.todolist.global.error

import com.meteor.todolist.global.error.common.CommonException
import com.meteor.todolist.global.error.common.CommonExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun commonExceptionHandler(e: CommonException) =
        ResponseEntity(
            CommonExceptionResponse(
                code = e.code,
                message = e.message!!,
                null
            ),
            e.status
        )

    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandler(e: RuntimeException): ResponseEntity<CommonExceptionResponse<Any>> {
        e.printStackTrace()
        return ResponseEntity(
            CommonExceptionResponse(
                code = "INTERNAL_SERVER_ERROR",
                message = e.message?: "알 수 없는 오류",
                null
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }


}