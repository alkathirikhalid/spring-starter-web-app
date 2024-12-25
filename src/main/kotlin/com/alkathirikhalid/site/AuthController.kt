package com.alkathirikhalid.site

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
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

//    @PostMapping("/login")
//    fun login(@ModelAttribute("user") user: User): String {
//        val validatedUser = userService.validateUser(user.email, user.password).orElse(null)
//        // Check if the user is valid, and handle session or redirect
//        if (validatedUser != null) {
//            // TODO: Set session or cookie for user login
//            return "redirect:/hello"
//        } else {
//            // Handle invalid login
//            return "redirect:/login"
//        }
//    }

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