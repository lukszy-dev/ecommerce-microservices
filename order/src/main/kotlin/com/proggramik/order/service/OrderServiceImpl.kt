package com.proggramik.order.service

import com.proggramik.order.domain.dto.GetOrderResponseDTO
import com.proggramik.order.repository.OrderRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
) : OrderService {
    override fun getOrder(id: Long): Mono<GetOrderResponseDTO> {
        return Mono.error(NotImplementedError())
    }
}
