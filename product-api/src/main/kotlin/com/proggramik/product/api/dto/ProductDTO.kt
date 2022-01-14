package com.proggramik.product.api.dto

data class ProductDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String?
)
