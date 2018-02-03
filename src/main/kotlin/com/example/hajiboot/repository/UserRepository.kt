package com.example.hajiboot.repository

import com.example.hajiboot.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}