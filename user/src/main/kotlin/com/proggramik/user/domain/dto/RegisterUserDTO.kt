package com.proggramik.user.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterUserDTO(
    @JsonProperty("name") val name: String,
    @JsonProperty("surname") val surname: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("confirmation") val confirmation: String?
)
