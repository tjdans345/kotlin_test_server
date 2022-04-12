package com.meteor.todolist.domain.user.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.meteor.todolist.domain.user.domain.dto.KaKaoData
import com.meteor.todolist.domain.user.domain.dto.UserLoginReq
import com.meteor.todolist.domain.user.domain.dto.UserRegisterReq
import com.meteor.todolist.domain.user.domain.entity.User
import com.meteor.todolist.domain.user.repository.UserRepository
import com.meteor.todolist.global.config.jwt.JwtTokenProvider
import org.springframework.boot.jackson.JsonObjectSerializer
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class UserService (private val userRepository: UserRepository, private val jwtTokenProvider: JwtTokenProvider) {

    fun getResponseFromKakao(kakaoAccessToken: String): KaKaoData {
        val mapper = jacksonObjectMapper()

        val url = URL("https://kapi.kakao.com/v2/user/me")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        con.setRequestProperty("Authorization", "Bearer $kakaoAccessToken")

        val responseCode: Int = con.responseCode


        if (responseCode != HttpStatus.OK.value())
            println("토큰 값 이상함!!!")

        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        br.close()


        val data = Gson().fromJson(content, KaKaoData::class.java)

        println(data)
        println(content)
        println(responseCode)

        return data;
    }

    fun findUser(email: String): User {
        return userRepository.findByEmail(email)
    }

    fun existsUser(email: String): Boolean {
        if(userRepository.existsByEmail(email)) {
            return true
        }
        return false
    }

    fun createUser(userRegisterReq: UserRegisterReq): User {
        val user = User(userRegisterReq.username ,userRegisterReq.userEmail, userRegisterReq.userPassword)
        return userRepository.save(user)
    }


    fun login(userLoginReq: UserLoginReq): String {
        val token: String = jwtTokenProvider.createToken(userLoginReq.userEmail)

        println(token)

        return token

    }

}