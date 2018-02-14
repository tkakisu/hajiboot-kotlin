package com.example.hajiboot.web

import com.example.hajiboot.service.CustomerService
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var customerService: CustomerService

    @Test
    fun list_未認証時はリダイレクトすること() {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isFound)
                .andExpect(redirectedUrlPattern("**/loginForm"))
                .andExpect(unauthenticated())
    }

    @Test
    @WithUserDetails(value = "user1", userDetailsServiceBeanName = "loginUserDetailsService")
    fun list_認証済み() {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk)
                .andExpect(view().name("customers/list"))
                .andExpect(content().string(containsString("<span>user1</span>さんログイン中。")))
                .andExpect(authenticated().withUsername("user1"))
    }

    @Test
    @WithUserDetails(value = "user2", userDetailsServiceBeanName = "loginUserDetailsService")
    fun create_トークンなし() {
        mockMvc.perform(post("/customers/create")
                .param("firstName", "Hidetoshi")
                .param("lastName", "Dekisugi"))
                .andExpect(status().isForbidden)

        val customer = customerService.findOne(4)
        assertNull(customer)
    }

    @Test
    @WithUserDetails(value = "user2", userDetailsServiceBeanName = "loginUserDetailsService")
    fun create_トークンあり() {
        mockMvc.perform(post("/customers/create")
                .param("firstName", "Hidetoshi")
                .param("lastName", "Dekisugi")
                .with(csrf()))
                .andExpect(status().isFound)
                .andExpect(view().name("redirect:/customers"))
                .andExpect(redirectedUrl("/customers"))
                .andExpect(authenticated().withUsername("user2"))

        val customer = customerService.findOne(4)!!
        assertNotNull(customer)
        assertThat(customer.firstName, equalTo("Hidetoshi"))
        assertThat(customer.lastName, equalTo("Dekisugi"))
        assertNotNull(customer.user)
        assertThat(customer.user!!.username, equalTo("user2"))
    }
}