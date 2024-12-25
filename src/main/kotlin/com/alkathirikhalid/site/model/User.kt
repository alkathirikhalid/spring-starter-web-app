package com.alkathirikhalid.site.model

import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    val email: String,

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: Set<String> = setOf("USER")
)