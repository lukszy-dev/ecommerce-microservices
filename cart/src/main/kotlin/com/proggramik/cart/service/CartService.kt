package com.proggramik.cart.service

import com.proggramik.cart.domain.Cart
import java.util.*

interface CartService {
    fun findCart(id: Long): Optional<Cart>
}
