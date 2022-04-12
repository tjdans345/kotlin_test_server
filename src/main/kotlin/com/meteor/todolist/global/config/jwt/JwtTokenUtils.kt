package com.meteor.todolist.global.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.Data
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.function.Function

class JwtTokenUtils (

    @Value("\${jwt.secret}") private val secretKey: String,
    // 토큰 유효시간
    @Value("\${jwt.expiration}") private val expiration: Long
) {

    companion object {
        const val AUDIENCE_MOBILE = "mobile"
    }

    fun getUsernameFromToken(token : String) : String? {

    }

    fun getIssuedAtDataFromToken(token: String) : Date {

    }

    fun getExpirationDateFromToken(token: String) : Date {

    }

    fun getAudienceFromToken(token: String) : String? {

    }

    fun <T> getClaimFromToken(token: String, claimResolver: Function<Claims, T>): T {

    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token);
        return expiration.before(Date());
    }

    private fun generateAudience() : String {
        return AUDIENCE_MOBILE
    }

    fun generateToken(userDetails : UserDetails) : String {
        val claims = mutableMapOf<String, Any>() // JWT payload 에 저장되는 정보단위

        // authority key 값으로 유저 권한정보 저장
        claims["authority"] = userDetails.authorities // 정보는 key / value 쌍으로 저장된다
        return doGenerateToken(claims, userDetails.username, generateAudience())
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String, audience : String): String {
        val now = Date()
        val expirationDate = calculateExpirationDate(now)

        println("doGenerateToken $now")

        return Jwts.builder()
            .setClaims(claims) // 정보 저장
            .setSubject(subject)
            .setAudience(audience)
            .setIssuedAt(now) // 토큰 발급 시간 (토큰의 age 가 얼마나 되었는지 판단 가능)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret 값 세팅
            .compact()


    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as Jwt
    }

    private fun calculateExpirationDate(createDate: Date) : Date {
        return Date(createDate.time + expiration * 1000)
    }

}