package com.proggramik.order.service

import com.proggramik.order.domain.dto.*
import reactor.core.publisher.Mono
import java.util.*

interface CartService {
    fun getCart(userId: UUID): Mono<GetCartResponseDTO>
    fun addItemToCart(request: AddItemToCartRequestDTO, userId: UUID): Mono<AddItemToCartResponseDTO>
    fun removeItemFromCart(request: RemoveItemFromCartRequestDTO, userId: UUID): Mono<RemoveItemFromCartResponseDTO>
}
