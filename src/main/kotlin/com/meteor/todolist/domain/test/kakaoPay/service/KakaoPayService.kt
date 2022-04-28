package com.meteor.todolist.domain.test.kakaoPay.service

import com.google.gson.Gson
import com.meteor.todolist.domain.test.kakaoPay.domain.request.CompleteInformation
import com.meteor.todolist.domain.test.kakaoPay.domain.request.PaymentInformation
import com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay.PayCompleteResponse
import com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay.Test
import com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay.TestResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class KakaoPayService {

    private final val key = "zzz"

    fun paymentReady(paymentInformation: PaymentInformation) : TestResponse {

        val url = "https://kapi.kakao.com/v1/payment/ready"
        val restTemplate = RestTemplate()
        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK $key")
        header.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        val entity = HttpEntity<String>(header);
        val partnerOrderId = UUID.randomUUID().toString().replace("-", "")
        val partnerUserId = "KIOSKSHOP_MEMEBER"

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("cid", "TC0ONETIME")
            .queryParam("partner_order_id", partnerOrderId)
            .queryParam("partner_user_id", partnerUserId)
            .queryParam("item_name", paymentInformation.itemName)
            .queryParam("quantity", paymentInformation.totalCount)
            .queryParam("total_amount", paymentInformation.totalPrice)
            .queryParam("tax_free_amount", "0")
            .queryParam("approval_url", "http://127.0.0.1:5500/payment/pages/kakao_pay_complete.html")
            .queryParam("cancel_url", "http://localhost:9090/kakao/cancel")
            .queryParam("fail_url", "http://localhost:9090/kakao/fail")
            .build()

        val responseData = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String::class.java)
        println("responseDate : ${responseData.body}")
        val payReadyData =  Gson().fromJson(responseData.body, Test::class.java)
        return TestResponse(payReadyData, partnerOrderId, partnerUserId)
    }

    fun paymentApproveRequest(completeInformation: CompleteInformation): PayCompleteResponse {
        val url = "https://kapi.kakao.com/v1/payment/approve"
        val restTemplate = RestTemplate()
        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK $key")
        header.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        val entity = HttpEntity<String>(header);

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("cid", "TC0ONETIME")
            .queryParam("tid", completeInformation.tid)
            .queryParam("partner_order_id", completeInformation.partnerOrderId)
            .queryParam("partner_user_id", completeInformation.partnerUserId)
            .queryParam("pg_token", completeInformation.pgToken)
            .build()

        val responseData = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, String::class.java)
        println("responseDate : ${responseData.body}")
        println("결제 완료 !!!")
        return Gson().fromJson(responseData.body, PayCompleteResponse::class.java)
    }

}

