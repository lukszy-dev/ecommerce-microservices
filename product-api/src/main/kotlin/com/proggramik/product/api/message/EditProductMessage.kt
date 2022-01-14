package com.proggramik.product.api.message

data class EditProductMessage(
    val id: Long,
    val name: String?,
    val price: Double?
)
