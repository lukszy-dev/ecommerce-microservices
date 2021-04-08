package com.proggramik.user.service.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterResponseDTO(
    @JsonProperty("created") val created: Boolean,
)
