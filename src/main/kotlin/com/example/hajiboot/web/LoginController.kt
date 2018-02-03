package com.example.hajiboot.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping(path = ["loginForm"])
    fun loginForm(): String {
        return "loginForm"
    }
}