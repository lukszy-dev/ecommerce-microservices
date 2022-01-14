package com.proggramik.order.service.client

import com.proggramik.product.api.dto.ProductDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "product-service", url = "http://\${product-service.url:product-service}")
interface ProductClient {
    @Deprecated(message = "Product table will be populated from Message Queue. Keeping this for future reference.")
    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): Mono<ProductDTO?>
}
