package com.meteor.todolist.global.config.security

import com.meteor.todolist.global.config.jwt.JwtAuthenticationFilter
import com.meteor.todolist.global.config.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig (private val jwtTokenProvider : JwtTokenProvider) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable() // rest api 만 고려, 기본 설정 해제
            .csrf().disable() // csrf 보안 토큰 disable 처리
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반이므로 세션 사용 x
            .and()
            .authorizeRequests() // 요청에 대한 사용권한 체크
            .antMatchers("/api/**", "/valid/**").authenticated()
            .antMatchers("/register/**", "/login/**", "/logout/**").permitAll() // 해당 경로는 누구나 접근 가능
            .and()
            .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }
}