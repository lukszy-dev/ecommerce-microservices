package com.proggramik.product.repository

import com.proggramik.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun removeById(id: Long): Long
}
