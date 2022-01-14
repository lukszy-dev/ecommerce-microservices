package com.proggramik.order.controller

import com.proggramik.order.domain.dto.*
import com.proggramik.order.security.CustomAuthenticationToken
import com.proggramik.order.service.CartService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    fun getCart(principal: Principal): Mono<GetCartResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        return cartService.getCart(userId)
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    fun addItemToCart(
        @RequestBody request: AddItemToCartRequestDTO,
        principal: Principal
    ): Mono<AddItemToCartResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        return cartService.addItemToCart(request, userId)
    }

    @PostMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    fun removeItemFromCart(
        @RequestBody request: RemoveItemFromCartRequestDTO,
        principal: Principal
    ): Mono<RemoveItemFromCartResponseDTO> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        return cartService.removeItemFromCart(request, userId)
    }
}
