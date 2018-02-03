package com.example.hajiboot.service

import com.example.hajiboot.domain.User
import org.springframework.security.core.authority.AuthorityUtils

data class LoginUserDetails(val user: User): org.springframework.security.core.userdetails.User(
        user.username, user.encodedPassword, AuthorityUtils.createAuthorityList("ROLE_USER")
)