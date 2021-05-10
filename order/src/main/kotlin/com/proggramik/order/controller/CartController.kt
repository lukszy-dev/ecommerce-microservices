package com.proggramik.order.controller

import com.proggramik.order.domain.dto.AddItemToCartRequestDTO
import com.proggramik.order.domain.dto.AddItemToCartResponseDTO
import com.proggramik.order.security.CustomAuthenticationToken
import com.proggramik.order.service.CartService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {
    @RequestMapping("/")
    @PreAuthorize("hasRole('USER')")
    fun getCart(): Mono<String> {
        return Mono.just("Cart")
    }

    @RequestMapping("/add")
    @PreAuthorize("hasRole('USER')")
    fun addProductToCart(
        @RequestBody request: AddItemToCartRequestDTO,
        principal: Principal
    ): Mono<AddItemToCartResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserUUID()
        return cartService.addProductToCart(request, userId)
    }
}
