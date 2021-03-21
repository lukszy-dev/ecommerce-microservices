package com.proggramik.user.service

import com.proggramik.user.domain.Product
import com.proggramik.user.domain.dto.AddProductDTO
import java.util.*

interface ProductService {
    fun listProducts(): List<Product>
    fun findProduct(id: Long): Optional<Product>
    fun addProduct(product: AddProductDTO)
}
