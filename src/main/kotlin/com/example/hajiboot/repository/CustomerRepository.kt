package com.example.hajiboot.repository

import com.example.hajiboot.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CustomerRepository: JpaRepository<Customer, Int> {
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    fun findAllOrderByName(): List<Customer>
}
