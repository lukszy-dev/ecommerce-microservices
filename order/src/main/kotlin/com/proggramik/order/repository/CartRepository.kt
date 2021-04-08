package com.proggramik.order.repository

import com.proggramik.order.domain.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.REQUIRED)
interface CartRepository : JpaRepository<Cart, Long>
