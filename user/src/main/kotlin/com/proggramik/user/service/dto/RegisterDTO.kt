package com.proggramik.user.service.dto

data class RegisterDTO(
    val email: String,
    val password: String,
    val userId: Long
)
