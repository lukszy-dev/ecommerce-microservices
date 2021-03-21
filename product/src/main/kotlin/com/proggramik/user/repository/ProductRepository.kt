package com.proggramik.user.repository

import com.proggramik.user.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface ProductRepository : JpaRepository<Product, Long>
