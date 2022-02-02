package com.proggramik.order.service

import com.proggramik.order.domain.Order
import com.proggramik.order.domain.OrderItem
import com.proggramik.order.domain.dto.CreateOrderResponseDTO
import com.proggramik.order.domain.dto.GetOrderResponseDTO
import com.proggramik.order.domain.dto.OrderItemDTO
import com.proggramik.order.repository.CartRepository
import com.proggramik.order.repository.OrderRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository
) : OrderService {
    override fun getOrder(id: Long, userId: Long): Mono<GetOrderResponseDTO> {
        orderRepository.findByIdAndUserId(id, userId)?.let { order ->
            val orderItems = order.orderItems.map {
                OrderItemDTO(it.product.name, it.subtotal.toDouble(), it.quantity)
            }
            return Mono.just(GetOrderResponseDTO(orderItems))
        }

        return Mono.error(
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Order with id: %d not found", id)
            )
        )
    }

    @Transactional
    override fun createOrder(userId: Long): Mono<CreateOrderResponseDTO> {
        cartRepository.findByUserId(userId)?.let { cart ->
            val order = orderRepository.save(Order(mutableSetOf(), userId))
            val orderItems = cart.cartItems.map {
                OrderItem(it.quantity, it.product.price.multiply(it.quantity.toBigDecimal()), it.product, order)
            }.toMutableSet()

            order.orderItems = orderItems
            orderRepository.save(order)

            return Mono.just(CreateOrderResponseDTO(order.id!!))
        }

        return Mono.error(
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Couldn't create order")
            )
        )
    }
}
