package com.proggramik.cart.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AddItemToCartRequestDTO(
    @JsonProperty("product_id") val productId: Long
)
