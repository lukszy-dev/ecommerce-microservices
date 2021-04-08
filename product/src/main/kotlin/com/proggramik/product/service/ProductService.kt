package com.proggramik.product.service

import com.proggramik.product.domain.Product
import com.proggramik.product.domain.dto.AddProductDTO
import java.util.*

interface ProductService {
    fun listProducts(): List<Product>
    fun findProduct(id: Long): Optional<Product>
    fun addProduct(product: AddProductDTO)
}
