package com.proggramik.order.service

import com.proggramik.order.domain.Cart
import java.util.*

interface CartService {
    fun findCart(id: Long): Optional<Cart>
}
