package com.meteor.todolist.domain.user.repository

import com.meteor.todolist.domain.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>{
     fun findByEmail(email: String): User
     fun existsByEmail(email: String): Boolean


}