package com.proggramik.product.service

import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.ProductDTO

interface ProductService {
    fun listProducts(): List<ProductDTO>
    fun findById(id: Long): ProductDTO?
    fun addProduct(request: AddProductRequestDTO)
}
