package com.proggramik.cart.service

import com.proggramik.cart.domain.Cart
import com.proggramik.cart.domain.CartItem
import com.proggramik.cart.domain.dto.AddItemToCartRequestDTO
import com.proggramik.cart.domain.dto.AddItemToCartResponseDTO
import com.proggramik.cart.repository.CartRepository
import com.proggramik.cart.service.client.ProductClient
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.util.*

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val productClient: ProductClient
) : CartService {
    override fun findCart(id: Long): Cart? {
        return cartRepository.findByIdOrNull(id)
    }

    override fun addProductToCart(request: AddItemToCartRequestDTO, userId: UUID): Mono<AddItemToCartResponseDTO> {
        return productClient.get(request.productId).map { product ->
            var cart: Cart? = cartRepository.findByUserId(userId)
            if (cart == null) {
                cart = cartRepository.save(Cart(userId))
            }

            cart.cartItems
            cart.cartItems?.add(CartItem(product.id, cart, request.quantity))
            cart = cartRepository.saveAndFlush(cart)

            AddItemToCartResponseDTO(cart.cartItems?.size)
        }.switchIfEmpty(
            Mono.error(
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Product with id: {id} not found", request.productId)
                )
            )
        )
    }
}
