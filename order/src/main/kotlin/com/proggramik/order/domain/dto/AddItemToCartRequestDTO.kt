package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AddItemToCartRequestDTO(
    @JsonProperty("product_id") val productId: Long,
    @JsonProperty("quantity") val quantity: Int
)
