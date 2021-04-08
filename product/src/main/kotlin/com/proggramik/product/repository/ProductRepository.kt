package com.proggramik.product.repository

import com.proggramik.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.REQUIRED)
interface ProductRepository : JpaRepository<Product, Long>
