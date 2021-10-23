package com.proggramik.product.service

import com.proggramik.product.domain.dto.*
import reactor.core.publisher.Mono

interface ProductService {
    fun listProducts(): List<ProductDTO>
    fun findById(id: Long): ProductDTO?
    fun addProduct(request: AddProductRequestDTO): Mono<AddProductResponseDTO>
    fun editProduct(id: Long, request: EditProductRequestDTO): Mono<Boolean>
    fun removeProduct(request: RemoveProductRequestDTO): Mono<Boolean>
}
