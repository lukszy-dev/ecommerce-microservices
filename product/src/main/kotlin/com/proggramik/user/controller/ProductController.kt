package com.proggramik.user.controller

import com.proggramik.user.domain.Product
import com.proggramik.user.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {
    @GetMapping("/products")
    fun products(): List<Product> {
        return productService.listProducts()
    }
}
