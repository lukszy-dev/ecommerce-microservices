package com.proggramik.order.service

import com.proggramik.order.domain.Cart
import com.proggramik.order.domain.dto.AddItemToCartRequestDTO
import com.proggramik.order.domain.dto.AddItemToCartResponseDTO
import reactor.core.publisher.Mono
import java.util.*

interface CartService {
    fun findCart(id: Long): Cart?
    fun addProductToCart(request: AddItemToCartRequestDTO, userId: UUID): Mono<AddItemToCartResponseDTO>
}
