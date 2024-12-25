package com.alkathirikhalid.site

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/", "/home", "/signup", "/forgot-password")
                    .permitAll()  // Allow signup and forgot-password without authentication
                    .anyRequest().authenticated()  // Require authentication for other requests
            }
            .formLogin { form ->
                form
                    .loginPage("/login") // Custom login page
                    .permitAll()
            }
            .logout { logout -> logout.permitAll() } // Allow logout without authentication


        return http.build()
    }

    @Bean
    fun userDetailsService(userService: UserService): UserDetailsService {
        return UserDetailsService { username ->
            val user = userService.findByUsername(username)
                ?: throw UsernameNotFoundException("User not found: $username")
            User(
                user.username,
                user.password,
                user.roles.map { SimpleGrantedAuthority(it) }
            )
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()  // You can choose other encoders as needed
    }
}