package com.proggramik.order.service

import com.proggramik.order.domain.dto.*
import reactor.core.publisher.Mono

interface CartService {
    fun getCart(userId: Long): Mono<GetCartResponseDTO>
    fun addItemToCart(request: AddItemToCartRequestDTO, userId: Long): Mono<AddItemToCartResponseDTO>
    fun removeItemFromCart(request: RemoveItemFromCartRequestDTO, userId: Long): Mono<RemoveItemFromCartResponseDTO>
}
