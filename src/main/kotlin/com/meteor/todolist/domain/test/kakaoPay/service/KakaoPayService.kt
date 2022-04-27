package com.meteor.todolist.domain.test.kakaoPay.service

import com.google.gson.Gson
import com.meteor.todolist.domain.test.kakaoPay.domain.request.PaymentInformation
import com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class KakaoPayService {

    fun paymentReady(paymentInformation: PaymentInformation) : Test {
        val key = "3c54ae1d020a61a419548e606d9b85e6"
        val url = "https://kapi.kakao.com/v1/payment/ready"
        val restTemplate = RestTemplate()
        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK $key")
        header.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        val entity = HttpEntity<String>(header);

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("cid", "TC0ONETIME")
            .queryParam("partner_order_id", UUID.randomUUID().toString().replace("-", ""))
            .queryParam("partner_user_id", "KIOSKSHOP_MEMEBER")
            .queryParam("item_name", paymentInformation.itemName)
            .queryParam("quantity", paymentInformation.totalCount)
            .queryParam("total_amount", paymentInformation.totalPrice)
            .queryParam("tax_free_amount", "0")
            .queryParam("approval_url", "http://localhost:9090/kakao/completed")
            .queryParam("cancel_url", "http://localhost:9090/kakao/cancel")
            .queryParam("fail_url", "http://localhost:9090/kakao/fail")
            .build()

        println("여기엔!!")
        val responseData = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String::class.java)
        println("responseDate : ${responseData.body}")

        return Gson().fromJson(responseData.body, Test::class.java)
    }

}

