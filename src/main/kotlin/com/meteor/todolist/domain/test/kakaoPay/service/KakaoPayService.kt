package com.meteor.todolist.domain.test.kakaoPay.service

import com.google.gson.Gson
import com.meteor.todolist.domain.test.kakaoPay.domain.request.PaymentInformation
import com.meteor.todolist.domain.user.domain.dto.naver.NaverData
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class KakaoPayService {

    fun paymentReady(paymentInformation: PaymentInformation) {
        val con: HttpURLConnection = URL("https://kapi.kakao.com/v1/payment/ready").openConnection() as HttpURLConnection
        con.requestMethod = "POST"
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        con.setRequestProperty("Authorization", "KakaoAK 09eafe34530e44aa9f3824f86a7fe62d")
        // 필수 파라미터
        con.setRequestProperty("cid", "KIOSKSHOP")
        con.setRequestProperty("partner_order_id", "")
        con.setRequestProperty("partner_user_id", "")
        con.setRequestProperty("item_name", "")
        con.setRequestProperty("quantity", "")
        con.setRequestProperty("total_amount", "")
        con.setRequestProperty("tax_free_amount", "")
        con.setRequestProperty("approval_url", "")
        con.setRequestProperty("cancel_url", "")
        con.setRequestProperty("fail_url", " 0")
        con.doOutput = true
        con.doInput = true


        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        br.close()

        return null
    }

}

