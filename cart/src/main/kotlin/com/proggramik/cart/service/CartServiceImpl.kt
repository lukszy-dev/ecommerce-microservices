package com.proggramik.cart.service

import com.proggramik.cart.domain.Cart
import com.proggramik.cart.repository.CartRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartServiceImpl(private val cartRepository: CartRepository): CartService {
    override fun findCart(id: Long): Optional<Cart> {
        return cartRepository.findById(id)
    }
}
