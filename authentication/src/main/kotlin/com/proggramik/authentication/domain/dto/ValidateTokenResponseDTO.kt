package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ValidateTokenResponseDTO(
    @JsonProperty("valid") val valid: Boolean
)
