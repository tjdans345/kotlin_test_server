package com.meteor.todolist.domain.user.controller

import com.meteor.todolist.domain.user.domain.dto.KaKaoData
import com.meteor.todolist.domain.user.domain.dto.KakaoTokenReq
import com.meteor.todolist.domain.user.domain.dto.UserLoginReq
import com.meteor.todolist.domain.user.domain.dto.UserRegisterReq
import com.meteor.todolist.domain.user.domain.entity.User
import com.meteor.todolist.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserApiController (private val userService: UserService, private val passwordEncoder: PasswordEncoder) {

    @PostMapping("/valid")
    fun tokenCheck(@RequestBody kakaoTokenReq: KakaoTokenReq) : ResponseEntity<KaKaoData> {
        return ResponseEntity.ok().body(userService.getResponseFromKakao(kakaoTokenReq.token))
    }

    @PostMapping("/register")
    fun register(@RequestBody userRegisterReq: UserRegisterReq): ResponseEntity<User> {

        if(userService.existsUser(userRegisterReq.userEmail)) {
            println("여긴데")
            throw Exception("no email");
        }
        userRegisterReq.userPassword = passwordEncoder.encode(userRegisterReq.userPassword)

        return ResponseEntity.ok(userService.createUser(userRegisterReq))
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginReq: UserLoginReq): ResponseEntity<String> {
        if(!userService.existsUser(userLoginReq.userEmail)) {
            throw Exception("no email");
        }

        val user: User = userService.findUser(userLoginReq.userEmail)

        if(!passwordEncoder.matches(userLoginReq.userPassword, user.password)) {
            throw Exception("비번 틀림")
        }

        return ResponseEntity.ok(userService.login(userLoginReq))
    }

}