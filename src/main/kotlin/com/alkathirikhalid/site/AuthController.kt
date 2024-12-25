package com.alkathirikhalid.site

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class AuthController(private val userService: UserService) {

    @GetMapping("/signup")
    fun signupForm(model: Model): String {
        model.addAttribute("user", User(username = "", password = "", email = ""))
        return "signup"
    }

    @PostMapping("/signup")
    fun signup(user: User): String {
        userService.saveUser(user)
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun loginForm(model: Model): String {
        model.addAttribute("user", User(username = "", password = "", email = ""))
        return "login"
    }

    @GetMapping("/forgot-password")
    fun forgotPasswordForm(): String {
        return "forgot-password"
    }

    @PostMapping("/forgot-password")
    fun processForgotPassword(email: String): String {
        // TODO Implement email-based password reset
        return "redirect:/login?resetSuccess"
    }
}