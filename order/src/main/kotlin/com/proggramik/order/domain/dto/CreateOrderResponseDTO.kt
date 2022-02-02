package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateOrderResponseDTO(
    @JsonProperty("order_id") val orderId: Long
)
