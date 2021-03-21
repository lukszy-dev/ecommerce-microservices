package com.proggramik.user.domain

import javax.persistence.*

@Entity
class User (
    val email: String,
    val password: String,
    val name: String = "",
    val surname: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
