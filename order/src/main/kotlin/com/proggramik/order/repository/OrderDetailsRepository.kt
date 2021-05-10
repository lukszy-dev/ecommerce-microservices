package com.proggramik.order.repository

import com.proggramik.order.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderDetailsRepository : JpaRepository<Order, Long>
