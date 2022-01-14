package com.proggramik.user.service.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterRequestDTO(
    val email: String,
    val password: String,
    @JsonProperty("user_id") val userId: Long
)
