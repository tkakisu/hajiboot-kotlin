package com.example.hajiboot.domain

import javax.persistence.*

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue
    var id: Int? = null,
    @Column(nullable = false)
    val firstName: String? = null,
    @Column(nullable = false)
    val lastName: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "username")
    var user: User? = null
)
