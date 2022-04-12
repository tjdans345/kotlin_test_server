package com.meteor.todolist.global.config.jwt

import com.meteor.todolist.global.enum.SocialType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser (
    private val uid : Long,
    private val nickname : String,
    private val username : String,
    private val socialType : SocialType,
    private val password: String,
    private val email: String,
    private val authorities : MutableList<out GrantedAuthority>
        ) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
       return password
    }

    override fun getUsername(): String {
      return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}