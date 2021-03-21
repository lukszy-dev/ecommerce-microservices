package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidateTokenRequestDTO(
    @JsonProperty("token") val token: String
)
