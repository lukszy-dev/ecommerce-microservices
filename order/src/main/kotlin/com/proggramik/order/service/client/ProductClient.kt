package com.proggramik.order.service.client

import com.proggramik.order.service.client.dto.ProductDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "product-service", url = "http://product-service")
interface ProductClient {
    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): Mono<ProductDTO>
}
