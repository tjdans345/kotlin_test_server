package com.meteor.todolist.domain.user.service

import com.google.gson.Gson
import com.meteor.todolist.domain.user.domain.dto.kakao.KaKaoData
import com.meteor.todolist.domain.user.domain.dto.UserLoginReq
import com.meteor.todolist.domain.user.domain.dto.UserRegisterReq
import com.meteor.todolist.domain.user.domain.dto.naver.NaverData
import com.meteor.todolist.domain.user.domain.entity.User
import com.meteor.todolist.domain.user.repository.UserRepository
import com.meteor.todolist.global.config.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class UserService (private val userRepository: UserRepository, private val jwtTokenProvider: JwtTokenProvider) {

    /**
     * 토큰 검증 및 유저 데이터 반환 (카카오)
     */
    fun getResponseFromKakao(kakaoAccessToken: String): KaKaoData {
        val con: HttpURLConnection = URL("https://kapi.kakao.com/v2/user/me").openConnection() as HttpURLConnection
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        con.setRequestProperty("Authorization", "Bearer $kakaoAccessToken")

        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        br.close()

        val data = Gson().fromJson(content, KaKaoData::class.java)

        return data;
    }

    /**
     * 토큰 검증 및 유저 데이터 반환 (네이버)
     */
    fun getResponseFromNaver(NaverAccessToken: String): NaverData {
        val con: HttpURLConnection = URL("https://openapi.naver.com/v1/nid/me").openConnection() as HttpURLConnection
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        con.setRequestProperty("Authorization", "Bearer $NaverAccessToken")

        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        br.close()

        return Gson().fromJson(content, NaverData::class.java);
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
        val user = User(userRegisterReq.username!! ,userRegisterReq.userEmail, userRegisterReq.userPassword)
        return userRepository.save(user)
    }


    fun login(userLoginReq: UserLoginReq): String {
        val token: String = jwtTokenProvider.createToken(userLoginReq.userEmail)

        println(token)

        return token

    }

}