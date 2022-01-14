package com.proggramik.product.controller

import com.proggramik.product.api.dto.ProductDTO
import com.proggramik.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/products")
    fun products(): Mono<List<ProductDTO>> {
        return Mono.just(productService.listProducts())
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): Mono<ProductDTO?> {
        return Mono.justOrEmpty(productService.findById(id))
    }
}
