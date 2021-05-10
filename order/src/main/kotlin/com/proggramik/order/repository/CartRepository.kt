package com.proggramik.order.repository

import com.proggramik.order.domain.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : JpaRepository<Cart, Long> {
    fun findByUserId(userId: UUID): Cart?
}
