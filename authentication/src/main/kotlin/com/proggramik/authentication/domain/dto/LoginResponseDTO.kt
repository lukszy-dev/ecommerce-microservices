package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponseDTO(
    @JsonProperty("token") val token: String
)
