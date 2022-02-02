package com.proggramik.order.service

import com.proggramik.order.domain.Cart
import com.proggramik.order.domain.CartItem
import com.proggramik.order.domain.dto.*
import com.proggramik.order.repository.CartRepository
import com.proggramik.order.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) : CartService {
    override fun getCart(userId: Long): Mono<GetCartResponseDTO> {
        var cart = cartRepository.findByUserId(userId)
        if (cart == null) {
            cart = cartRepository.save(Cart(userId))
        }

        val cartItems = cart.cartItems.map { item ->
            CartItemDTO(item.product.id, item.product.name, item.product.price.toDouble(), item.quantity)
        }
        return Mono.just(GetCartResponseDTO(cartItems))
    }

    @Transactional
    override fun addItemToCart(request: AddItemToCartRequestDTO, userId: Long): Mono<AddItemToCartResponseDTO> {
        productRepository.findByIdOrNull(request.productId)?.let { product ->
            var cart = cartRepository.findByUserId(userId)
            if (cart == null) {
                cart = cartRepository.save(Cart(userId))
            }

            val cartItem = cart.cartItems.find { item -> item.product.id == product.id }
            if (cartItem != null) {
                cartItem.quantity = request.quantity
            } else {
                cart.cartItems.add(CartItem(product, cart, request.quantity))
            }

            cartRepository.save(cart)

            return Mono.just(AddItemToCartResponseDTO(cart.cartItems.size))
        }

        return Mono.error(
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Product with id: %d not found", request.productId)
            )
        )
    }

    @Transactional
    override fun removeItemFromCart(
        request: RemoveItemFromCartRequestDTO,
        userId: Long
    ): Mono<RemoveItemFromCartResponseDTO> {
        val cart = cartRepository.findByUserId(userId)
            ?: return Mono.error(
                ResponseStatusException(HttpStatus.NOT_FOUND, "Cart doesn't exist")
            )

        cart.cartItems.removeIf { item -> item.product.id == request.productId }

        return Mono.just(RemoveItemFromCartResponseDTO(cart.cartItems.size))
    }
}
