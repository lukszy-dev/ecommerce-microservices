package com.proggramik.order.domain.dto

data class OrderItemDTO(
    val name: String,
    val subtotal: Double,
    val quantity: Int
)
