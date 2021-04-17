package com.proggramik.cart.service

import com.proggramik.cart.domain.Cart
import com.proggramik.cart.domain.dto.AddItemToCartRequestDTO
import com.proggramik.cart.domain.dto.AddItemToCartResponseDTO
import reactor.core.publisher.Mono
import java.util.*

interface CartService {
    fun findCart(id: Long): Cart?
    fun addProductToCart(request: AddItemToCartRequestDTO, userId: UUID): Mono<AddItemToCartResponseDTO>
}
