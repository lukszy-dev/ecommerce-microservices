package com.proggramik.order.domain.dto

data class AddressDetailsDTO(
    val name: String,
    val surname: String,
    val street: String,
    val city: String,
    val zipCode: String,
    val country: String,
    val phone: String
)
