package com.proggramik.cart.repository

import com.proggramik.cart.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.REQUIRED)
interface OrderRepository : JpaRepository<Order, Long>
