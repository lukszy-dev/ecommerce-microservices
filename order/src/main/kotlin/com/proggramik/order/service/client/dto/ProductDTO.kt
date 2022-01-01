package com.proggramik.order.service.client.dto

data class ProductDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String?
)
