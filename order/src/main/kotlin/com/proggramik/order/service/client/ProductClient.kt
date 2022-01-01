package com.proggramik.order.service.client

import com.proggramik.order.service.client.dto.ProductDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "product-service", url = "http://\${product-service.url:product-service}")
interface ProductClient {
    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): Mono<ProductDTO?>

    @PostMapping("/find-all")
    fun findAllById(@RequestBody request: Long): Mono<List<ProductDTO>>
}
