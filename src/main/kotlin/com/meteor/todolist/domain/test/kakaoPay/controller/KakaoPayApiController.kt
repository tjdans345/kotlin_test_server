package com.meteor.todolist.domain.test.kakaoPay.controller

import com.meteor.todolist.domain.test.kakaoPay.domain.request.PaymentInformation
import com.meteor.todolist.domain.test.kakaoPay.service.KakaoPayService
import com.meteor.todolist.global.common.resoponse.ResponseDTO
import com.meteor.todolist.global.common.resoponse.ResponseEnum
import lombok.extern.slf4j.Slf4j
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
        println(paymentInformation.itemName)
        println(paymentInformation.totalCount)
        println(paymentInformation.totalPrice)
        println(paymentInformation.productList)
        println("화긴")

        return ResponseEntity.ok()
            .body(ResponseDTO(ResponseEnum.EXAMPLE_ENUM2, kakaoPayService.paymentReady(paymentInformation)))
    }


    @GetMapping("/completed")
    fun approvalMethod(@RequestParam("pg_token") pgToken: String,) {
        println(pgToken)
    }

    @GetMapping("/cancel")
    fun cancelMethod() {

    }

    @GetMapping("/fail")
    fun failMethod() {

    }

}