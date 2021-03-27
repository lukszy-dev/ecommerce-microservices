package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequestDTO(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)
