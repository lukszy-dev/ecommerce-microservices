package com.proggramik.product.controller

import com.proggramik.product.domain.dto.AddProductDTO
import com.proggramik.product.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(private val productService: ProductService) {
    @PostMapping("/add")
    fun addProduct(@RequestBody product: AddProductDTO) {
        productService.addProduct(product)
    }
}
