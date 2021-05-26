package com.proggramik.product.service

import com.proggramik.product.domain.Product
import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.AddProductResponseDTO
import com.proggramik.product.domain.dto.ProductDTO
import com.proggramik.product.domain.dto.RemoveProductRequestDTO
import com.proggramik.product.repository.ProductRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun listProducts(): List<ProductDTO> {
        return productRepository.findAll().map { product ->
            ProductDTO(product.id!!, product.name, product.description)
        }
    }

    override fun findById(id: Long): ProductDTO? {
        return productRepository.findByIdOrNull(id)?.let { product ->
            ProductDTO(product.id!!, product.name, product.description)
        }
    }

    override fun addProduct(request: AddProductRequestDTO): Mono<AddProductResponseDTO> {
        val product = productRepository.save(Product(request.name, BigDecimal(request.price)))
        return Mono.just(AddProductResponseDTO(product.id))
    }

    override fun removeProduct(request: RemoveProductRequestDTO): Mono<Boolean> {
        return try {
            productRepository.deleteById(request.productId)
            Mono.just(true)
        } catch (exception: EmptyResultDataAccessException) {
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, exception.message, exception))
        }
    }
}
