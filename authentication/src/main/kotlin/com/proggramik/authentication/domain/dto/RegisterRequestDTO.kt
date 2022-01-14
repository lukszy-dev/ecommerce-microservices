package com.proggramik.authentication.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterRequestDTO(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("user_id") val userId: Long
)
