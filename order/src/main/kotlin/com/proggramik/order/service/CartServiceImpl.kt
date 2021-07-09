package com.proggramik.order.service

import com.proggramik.order.domain.Cart
import com.proggramik.order.domain.CartItem
import com.proggramik.order.domain.dto.*
import com.proggramik.order.repository.CartRepository
import com.proggramik.order.service.client.ProductClient
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.util.*

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val productClient: ProductClient
) : CartService {
    override fun getCart(userId: UUID): Mono<GetCartResponseDTO> {
        var cart = cartRepository.findByUserId(userId)
        if (cart == null) {
            cart = cartRepository.save(Cart(userId))
        }

        val cartItems = cart.cartItems.map { item -> CartItemDTO(item.productId, item.quantity) }
        return Mono.just(GetCartResponseDTO(cartItems))
    }

    @Transactional
    override fun addItemToCart(request: AddItemToCartRequestDTO, userId: UUID): Mono<AddItemToCartResponseDTO> {
        return productClient.get(request.productId).map { product ->
            var cart = cartRepository.findByUserId(userId)
            if (cart == null) {
                cart = cartRepository.save(Cart(userId))
            }

            val cartItem = cart.cartItems.find { item -> item.productId == product.id }
            if (cartItem != null) {
                cartItem.quantity = request.quantity
            } else {
                cart.cartItems.add(CartItem(product.id, cart, request.quantity))
            }

            cartRepository.save(cart)

            AddItemToCartResponseDTO(cart.cartItems.size)
        }.switchIfEmpty(
            Mono.error(
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Product with id: {id} not found", request.productId)
                )
            )
        )
    }

    @Transactional
    override fun removeItemFromCart(
        request: RemoveItemFromCartRequestDTO,
        userId: UUID
    ): Mono<RemoveItemFromCartResponseDTO> {
        val cart = cartRepository.findByUserId(userId)
            ?: return Mono.error(
                ResponseStatusException(HttpStatus.NOT_FOUND, "Cart doesn't exist")
            )

        cart.cartItems.removeIf { item -> item.productId == request.productId }

        return Mono.just(RemoveItemFromCartResponseDTO(cart.cartItems.size))
    }
}
