package com.proggramik.product.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

class RemoveProductRequestDTO(
    @JsonProperty("product_id") val productId: Long
)
