package com.proggramik.product.api.message

data class AddProductMessage(
    val id: Long,
    val name: String,
    val price: Double
)
