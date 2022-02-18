package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentInitRequestDTO(
    @JsonProperty("order_id") val orderId: Long
)
