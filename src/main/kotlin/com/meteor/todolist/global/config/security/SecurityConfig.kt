package com.meteor.todolist.global.config.security

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService

@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
class SecurityConfig {
    private val jwtAuthenticationTokenFilter : JwtAuthenticationToken
}