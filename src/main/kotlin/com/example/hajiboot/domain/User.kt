package com.example.hajiboot.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id
    var username: String? = null,
    @JsonIgnore
    var encodedPassword: String? = null,
    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "user")
    var customers: List<Customer>? = null
)