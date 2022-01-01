package com.proggramik.order.domain.dto

data class CartItemDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val quantity: Int
)
