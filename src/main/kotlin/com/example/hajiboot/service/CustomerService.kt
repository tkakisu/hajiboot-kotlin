package com.example.hajiboot.service

import com.example.hajiboot.domain.Customer
import com.example.hajiboot.domain.User
import com.example.hajiboot.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CustomerService(@Autowired val customerRepository: CustomerRepository) {

    fun findAll() = customerRepository.findAllOrderByName()

    fun findOne(id: Int): Customer? = customerRepository.findOne(id)

    fun create(customer: Customer, user: User): Customer {
        customer.user = user
        return customerRepository.save(customer)
    }

    fun update(customer: Customer, user: User): Customer {
        customer.user = user
        return customerRepository.save(customer)
    }

    fun delete(id: Int) {
        customerRepository.delete(id)
    }
}