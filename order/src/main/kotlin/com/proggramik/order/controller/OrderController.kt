package com.proggramik.order.controller

import com.proggramik.order.domain.dto.CreateOrderRequestDTO
import com.proggramik.order.domain.dto.CreateOrderResponseDTO
import com.proggramik.order.domain.dto.GetOrderResponseDTO
import com.proggramik.order.security.CustomAuthenticationToken
import com.proggramik.order.service.OrderService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    fun getOrder(@PathVariable("id") id: Long, principal: Principal): Mono<GetOrderResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        return orderService.getOrder(id, userId)
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    fun createOrder(@RequestBody request: CreateOrderRequestDTO, principal: Principal): Mono<CreateOrderResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        return orderService.createOrder(userId)
    }
}
