package com.proggramik.product.service

import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.AddProductResponseDTO
import com.proggramik.product.domain.dto.ProductDTO
import com.proggramik.product.domain.dto.RemoveProductRequestDTO
import reactor.core.publisher.Mono

interface ProductService {
    fun listProducts(): List<ProductDTO>
    fun findById(id: Long): ProductDTO?
    fun addProduct(request: AddProductRequestDTO): Mono<AddProductResponseDTO>
    fun removeProduct(request: RemoveProductRequestDTO): Mono<Boolean>
}
