package com.alkathirikhalid.site.repository

import com.alkathirikhalid.site.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun saveUser(user: User): User {
        // TODO Data Validation
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username).orElse(null)
    }

    fun validateUser(email: String, password: String): Optional<User> {
        return userRepository.findByEmailAndPassword(email,passwordEncoder.encode(password))
    }
}