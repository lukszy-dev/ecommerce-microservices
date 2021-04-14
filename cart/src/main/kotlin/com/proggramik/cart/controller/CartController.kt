package com.proggramik.cart.controller

import com.proggramik.cart.domain.dto.AddItemToCartRequestDTO
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CartController {
    @RequestMapping("/cart")
    @PreAuthorize("hasRole('USER')")
    fun getCart(): Mono<String> {
        return Mono.just("Cart")
    }

    @RequestMapping("/add")
    @PreAuthorize("hasRole('USER')")
    fun addProductToCart(request: AddItemToCartRequestDTO) {

    }
}
