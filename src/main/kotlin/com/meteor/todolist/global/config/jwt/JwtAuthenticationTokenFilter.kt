package com.meteor.todolist.global.config.jwt

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

/**
 * JwtUtil 을 이용해서 헤더에서 JWT Token 을 받아와 유효한 토큰인지 확인,
 * JWT Token 이 유효할 경우 유저 정보를 SecurityContextHolder 에 저장하는 JwtAuthenticationFilter 생성
 */
@Component
class JwtAuthenticationTokenFilter (
    private val userDetailsService: UserDetailsService,
    private val jwtTokenUtils
        ) {

}