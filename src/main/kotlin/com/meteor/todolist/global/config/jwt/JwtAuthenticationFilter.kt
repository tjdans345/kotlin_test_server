package com.meteor.todolist.global.config.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * JwtTokenProvider 를 이용해 헤더에서 JWT 를 받아와 유효한 토큰인지 확인 -> 유효할 경우 유저 정보 SecurityContextHolder 에 저장
 */
class JwtAuthenticationFilter (private val jwtTokenProvider: JwtTokenProvider): GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        // Header 에서 JWT 토큰 꺼내오기
        val token: String? = jwtTokenProvider.resolveToken((request as HttpServletRequest))

        // 토큰 유효성 검증
        if(token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰에서 유저 정보를 받아옴
            val authentication = jwtTokenProvider.getAuthentication(token)
            // SecurityContext 에 Authentication 객체를 저장함.
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)


    }
}