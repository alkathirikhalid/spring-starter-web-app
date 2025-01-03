package com.alkathirikhalid.site.repository

import com.alkathirikhalid.site.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
    fun findByEmailAndPassword(email: String, password: String): Optional<User>
}