package com.proggramik.product.controller

import com.proggramik.product.domain.Product
import com.proggramik.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {
    @GetMapping("/products")
    fun products(): List<Product> {
        return productService.listProducts()
    }
}
