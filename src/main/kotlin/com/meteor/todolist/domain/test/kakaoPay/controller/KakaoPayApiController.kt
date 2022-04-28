package com.meteor.todolist.domain.test.kakaoPay.controller

import com.meteor.todolist.domain.test.kakaoPay.domain.request.CompleteInformation
import com.meteor.todolist.domain.test.kakaoPay.domain.request.PaymentInformation
import com.meteor.todolist.domain.test.kakaoPay.service.KakaoPayService
import com.meteor.todolist.global.common.resoponse.ResponseDTO
import com.meteor.todolist.global.common.resoponse.ResponseEnum
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kakao")
class KakaoPayApiController (
    private val kakaoPayService: KakaoPayService
        ) {

    @GetMapping("/test")
    fun getTodos(): HttpEntity<Any> {
        println("화긴")
        return ResponseEntity.ok()
            .body(ResponseDTO(ResponseEnum.EXAMPLE_ENUM2, "성공"))
    }

    @PostMapping("/test")
    fun postTest(@RequestBody paymentInformation: PaymentInformation): HttpEntity<Any> {

        return ResponseEntity.ok()
            .body(ResponseDTO(ResponseEnum.EXAMPLE_ENUM2, kakaoPayService.paymentReady(paymentInformation)))
    }


    @PostMapping("/completed")
    fun approvalMethod(@RequestBody completeInformation: CompleteInformation,): HttpEntity<Any> {
        println(completeInformation)

        return ResponseEntity.ok()
            .body(ResponseDTO(ResponseEnum.EXAMPLE_ENUM2, kakaoPayService.paymentApproveRequest(completeInformation)))
    }

    @GetMapping("/cancel")
    fun cancelMethod() {

    }

    @GetMapping("/fail")
    fun failMethod() {

    }

}