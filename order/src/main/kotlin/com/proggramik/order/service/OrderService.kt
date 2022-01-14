package com.proggramik.order.service

import com.proggramik.order.domain.dto.GetOrderResponseDTO
import reactor.core.publisher.Mono

interface OrderService {
    fun getOrder(id: Long): Mono<GetOrderResponseDTO>
}
