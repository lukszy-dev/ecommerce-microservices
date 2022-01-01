package com.proggramik.product.controller

import com.proggramik.product.domain.dto.FindAllRequestDTO
import com.proggramik.product.domain.dto.ProductDTO
import com.proggramik.product.service.ProductService
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/find-all")
    fun findAllById(@RequestBody request: FindAllRequestDTO): Mono<List<ProductDTO>> {
        return Mono.just(productService.findAllById(request))
    }
}
