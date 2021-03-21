package com.proggramik.order.domain

import javax.persistence.*

@Entity
data class Cart (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
