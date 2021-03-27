package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterResponseDTO(
    @JsonProperty("created") val created: Boolean,
)
