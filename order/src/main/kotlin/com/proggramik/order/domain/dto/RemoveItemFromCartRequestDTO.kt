package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RemoveItemFromCartRequestDTO(
    @JsonProperty("product_id") val productId: Long
)
