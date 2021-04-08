package com.proggramik.product.domain

import javax.persistence.*

@Entity
data class Product (
    val name: String,
    val description: String? = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
