package com.proggramik.authentication.domain.dto

data class RegisterDTO(
    val email: String,
    val password: String,
    val userId: Long
)
