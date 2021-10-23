package com.proggramik.product.controller

import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.AddProductResponseDTO
import com.proggramik.product.domain.dto.EditProductRequestDTO
import com.proggramik.product.domain.dto.RemoveProductRequestDTO
import com.proggramik.product.service.ProductService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
class AdminController(
    private val productService: ProductService
) {
    @PostMapping("/add")
    fun addProduct(@RequestBody request: AddProductRequestDTO): Mono<AddProductResponseDTO> {
        return productService.addProduct(request)
    }

    @PostMapping("/edit/{id}")
    fun editProduct(
        @PathVariable("id") id: Long,
        @RequestBody request: EditProductRequestDTO
    ): Mono<Boolean> {
        return productService.editProduct(id, request)
    }

    @PostMapping("/remove")
    fun removeProduct(@RequestBody request: RemoveProductRequestDTO): Mono<Boolean> {
        return productService.removeProduct(request)
    }
}
