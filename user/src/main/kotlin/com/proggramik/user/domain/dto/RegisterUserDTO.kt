package com.proggramik.user.domain.dto

data class RegisterUserDTO(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val confirmation: String
)
