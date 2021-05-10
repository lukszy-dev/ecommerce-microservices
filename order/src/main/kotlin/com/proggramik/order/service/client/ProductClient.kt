package com.proggramik.order.service.client

import com.proggramik.order.service.client.dto.ProductDTO
import reactor.core.publisher.Mono

interface ProductClient {
    fun get(id: Long): Mono<ProductDTO>
}
