package com.meteor.todolist.global.config.jwt

import com.meteor.todolist.domain.user.service.UserDetailService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

/**
 * JWT 토큰을 발급하고, 인증 정보를 조회하고, 회원 정보를 추출하는 JwtTokenProvider 를 생성
 */
@Component
class JwtTokenProvider(private val userDetailsService: UserDetailService) {

     private var secretKey: String = "meteor"

    // 30분
    private val tokenValidTime = 30 * 60 * 1000L

    // 객체 초기화, secretKey -> Base64 Encoding
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    // JWT 토큰 생성
    fun createToken(userPk: String) : String {
        val claims: Claims = Jwts.claims().setSubject(userPk) // JWT payload 에 저장되는 정보단위
        claims["userPk"] = userPk // 정보는 key, value 쌍으로 저장
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidTime)) // 만료시간 설정
            .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret 값 세팅
            .compact()
    }

    // JWT 토큰에서 인증 정보 조회
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰에서 회원 정보 추출
    fun getUserPk(token: String) :String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).body.subject
    }

    // Request Header 에서 token 값을 가져옴
    fun resolveToken(request: HttpServletRequest) : String? {
        return request.getHeader("Authorization")
    }

    // 토큰 유효성 + 만료 일자 확인
    fun validateToken(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e : Exception) {
            false
        }
    }




}