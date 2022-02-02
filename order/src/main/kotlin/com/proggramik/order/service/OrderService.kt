package com.proggramik.order.service

import com.proggramik.order.domain.dto.CreateOrderResponseDTO
import com.proggramik.order.domain.dto.GetOrderResponseDTO
import reactor.core.publisher.Mono

interface OrderService {
    fun getOrder(id: Long, userId: Long): Mono<GetOrderResponseDTO>
    fun createOrder(userId: Long): Mono<CreateOrderResponseDTO>
}
