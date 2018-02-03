package com.example.hajiboot.repository

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    fun findAllOrderByName_件数() {
        val customers = customerRepository.findAllOrderByName()
        // V2__import-initial-data.sqlではなく、data.sqlからデータを生成していることも確認。
        assertEquals(3, customers.size)
    }

    @Test
    fun findAllOrderByName_ソート順() {
        val customers = customerRepository.findAllOrderByName()
        assertEquals("Shizuka", customers[0].firstName)
        assertEquals("Suneo", customers[1].firstName)
        assertEquals("Takeshi", customers[2].firstName)
    }
}