package com.example.hajiboot.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    val username: String? = null,
    @JsonIgnore
    val encodedPassword: String? = null,
    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user")
    val customers: List<Customer>? = null
)