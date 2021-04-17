package com.proggramik.cart.service.client

import com.proggramik.cart.service.client.dto.ProductDTO
import reactor.core.publisher.Mono

interface ProductClient {
    fun get(id: Long): Mono<ProductDTO>
}
