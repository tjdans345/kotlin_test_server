package com.meteor.todolist.domain.user.domain.entity

import com.meteor.todolist.global.common.entity.BaseTime
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User(name: String, email: String, m_password: String): BaseTime(), UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String = name

    @Column(nullable = false, unique = true)
    var email: String = email

    @Column(nullable = false)
    var m_password: String = m_password

//    @Column(nullable = false)
//    var providerName: String = provider
//
//    fun providerName() : String {
//        return providerName
//    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return m_password
    }

    override fun getUsername(): String {
        return email
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

    override fun orElseThrow(function: () -> Unit): UserDetails {
        TODO("Not yet implemented")
    }
}