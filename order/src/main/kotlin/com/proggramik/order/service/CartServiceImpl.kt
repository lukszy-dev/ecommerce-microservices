package com.proggramik.order.service

import com.proggramik.order.domain.Cart
import com.proggramik.order.repository.CartRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartServiceImpl(private val cartRepository: CartRepository): CartService {
    override fun findCart(id: Long): Optional<Cart> {
        return cartRepository.findById(id)
    }
}
