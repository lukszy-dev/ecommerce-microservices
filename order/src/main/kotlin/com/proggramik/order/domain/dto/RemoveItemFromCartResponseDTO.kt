package com.proggramik.order.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RemoveItemFromCartResponseDTO(
    @JsonProperty("cart_items_cnt") val cartItemsCount: Int? = 0
)
