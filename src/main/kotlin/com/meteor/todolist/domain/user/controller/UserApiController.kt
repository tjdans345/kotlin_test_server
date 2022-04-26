package com.meteor.todolist.domain.user.controller

import com.meteor.todolist.domain.user.domain.dto.kakao.KaKaoData
import com.meteor.todolist.domain.user.domain.dto.TokenReq
import com.meteor.todolist.domain.user.domain.dto.UserLoginReq
import com.meteor.todolist.domain.user.domain.dto.UserRegisterReq
import com.meteor.todolist.domain.user.domain.dto.naver.NaverData
import com.meteor.todolist.domain.user.domain.entity.User
import com.meteor.todolist.domain.user.service.UserService
import com.meteor.todolist.global.aop.CustomAnnotation
import com.meteor.todolist.global.aop.CustomAnnotationTest
import com.meteor.todolist.global.common.resoponse.MEDIA_TYPE_APPLICATION_JSON_UTF8
import com.meteor.todolist.global.common.resoponse.ResponseDTO
import com.meteor.todolist.global.common.resoponse.ResponseEnum
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class UserApiController (private val userService: UserService, private val passwordEncoder: PasswordEncoder) {

    @CustomAnnotationTest
    @CustomAnnotation
    @GetMapping
    fun test() {
        println("zzzz")
    }

    @PostMapping("/kakao")
    fun kakaoTokenCheck(@RequestBody tokenReq: TokenReq) : ResponseEntity<KaKaoData> {
        return ResponseEntity.ok().body(userService.getResponseFromKakao(tokenReq.token))
    }

    @PostMapping("/naver")
    fun naverTokenCheck(@RequestBody tokenReq: TokenReq) : ResponseEntity<NaverData> {

        return ResponseEntity.ok().body(userService.getResponseFromNaver(tokenReq.token))
    }

    @PostMapping("/register")
    fun register(@RequestBody @Validated  userRegisterReq: UserRegisterReq, bindingResult: BindingResult): ResponseEntity<ResponseDTO<User>> {

        if(userService.existsUser(userRegisterReq.userEmail)) {
            throw Exception("이미 가입되어있는 이메일 입니다.");
        }
        userRegisterReq.userPassword = passwordEncoder.encode(userRegisterReq.userPassword)

        return ResponseEntity.ok().contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(ResponseDTO(data = userService.createUser(userRegisterReq), responseEnum = ResponseEnum.EXAMPLE_ENUM2))
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated userLoginReq: UserLoginReq): ResponseEntity<String> {

//        val userInfo = userService.existsUser(userLoginReq.userEmail);
//        userInfo.also {
//            check(it) { throw Exception("ddasdsa")}
//        }

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