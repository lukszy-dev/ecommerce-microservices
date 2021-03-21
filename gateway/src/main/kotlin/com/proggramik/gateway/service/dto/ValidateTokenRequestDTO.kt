package com.proggramik.gateway.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidateTokenRequestDTO(
    @JsonProperty("token") val token: String
)
