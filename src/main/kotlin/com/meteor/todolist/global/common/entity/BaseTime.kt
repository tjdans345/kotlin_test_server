package com.meteor.todolist.global.common.entity

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime {
    abstract fun orElseThrow(function: () -> Unit): UserDetails

    @CreationTimestamp
    @Column(nullable = false, updatable = false) // 수정되었을 때 날짜가 최신화되지 않게 하기 위해서 updatable false 로 설정
    val createAt : LocalDateTime? = null

    @CreationTimestamp
    @Column(nullable = false)
    val updateAt: LocalDateTime? = null



}