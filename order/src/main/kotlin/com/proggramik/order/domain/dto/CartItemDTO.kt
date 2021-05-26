package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CartItemDTO(
    @JsonProperty("product_id") val id: Long,
    val quantity: Int
)
