package com.proggramik.product.domain.dto

data class AddProductRequestDTO(
    val name: String,
    val price: Double,
    val description: String?
)
