package com.example.hajiboot.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.constraints.NotNull

@RunWith(SpringRunner::class)
class CustomerFormTest {
    private lateinit var validator: Validator

    @Before
    fun setUp() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @After
    fun tearDown() {
    }

    @Test
    fun firstNameの必須テスト() {
        val violations = validator.validate(CustomerForm(null, "Taro"))
        assertEquals(1, violations.size)
        violations.forEach {
            assertThat(it.constraintDescriptor.annotation).isInstanceOf(NotNull::class.java)
        }
    }

    @Test
    fun firstNameの最小桁数未満テスト() {
        val violations = validator.validate(CustomerForm("", "Taro"))
        assertEquals(1, violations.size)
    }

    @Test
    fun firstNameの最小桁数テスト() {
        val violations = validator.validate(CustomerForm("1", "Taro"))
        assertEquals(0, violations.size)
    }

    @Test
    fun firstNameの最大桁数テスト() {
        val violations = validator.validate(CustomerForm("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "Taro"))
        assertEquals(0, violations.size)
    }

    @Test
    fun firstNameの最大桁数超過テスト() {
        val violations = validator.validate(CustomerForm("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678", "Taro"))
        assertEquals(1, violations.size)
    }

    @Test
    fun lastNameの必須テスト() {
        val violations = validator.validate(CustomerForm("Yamada", null))
        assertEquals(1, violations.size)
        violations.forEach {
            assertThat(it.constraintDescriptor.annotation).isInstanceOf(NotNull::class.java)
        }
    }

    @Test
    fun lastNameの最小桁数未満テスト() {
        val violations = validator.validate(CustomerForm("Yamada", ""))
        assertEquals(1, violations.size)
    }

    @Test
    fun lastNameの最小桁数テスト() {
        val violations = validator.validate(CustomerForm("Yamada", "1"))
        assertEquals(0, violations.size)
    }

    @Test
    fun lastNameの最大桁数テスト() {
        val violations = validator.validate(CustomerForm("Yamada", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567"))
        assertEquals(0, violations.size)
    }

    @Test
    fun lastNameの最大桁数超過テスト() {
        val violations = validator.validate(CustomerForm("Yamada", "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678"))
        assertEquals(1, violations.size)
    }
}