package com.proggramik.product.controller

import com.proggramik.product.domain.Product
import com.proggramik.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/products")
    fun products(): Mono<List<Product>> {
        return Mono.just(productService.listProducts())
    }
}
