package com.proggramik.product.domain.dto

data class ProductDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String?
)
