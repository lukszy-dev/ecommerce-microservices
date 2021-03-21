package com.proggramik.gateway.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidateTokenResponseDTO(
    @JsonProperty("valid") val valid: Boolean
)
