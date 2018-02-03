package com.example.hajiboot.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class LoginUserDetailsServiceTest {
    @Autowired
    lateinit var loginUserDetailsService: LoginUserDetailsService

    @get:Rule
    val thrown = ExpectedException.none()!!

    @Test
    fun loadUserByUsername_一致() {
        val userDetails = loginUserDetailsService.loadUserByUsername("user1")
        assertNotNull(userDetails)
        assertEquals("user1", userDetails.username)
    }

    @Test
    fun loadUserByUsername_不一致() {
        thrown.expect(UsernameNotFoundException::class.java)
        loginUserDetailsService.loadUserByUsername("user3")
    }

    @Test
    fun loadUserByUsername_NULL() {
        thrown.expect(InvalidDataAccessApiUsageException::class.java)
        loginUserDetailsService.loadUserByUsername(null)
    }
}