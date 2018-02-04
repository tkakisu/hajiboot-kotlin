package com.example.hajiboot.web

import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(LoginController::class)
class LoginControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun loginForm() {
        mockMvc.perform(get("/loginForm"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("ログインフォーム")))
    }
}