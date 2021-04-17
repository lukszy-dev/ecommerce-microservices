package com.proggramik.cart.repository

import com.proggramik.cart.domain.OrderDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailsRepository : JpaRepository<OrderDetails, Long>
