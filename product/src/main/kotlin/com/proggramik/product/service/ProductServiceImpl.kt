package com.proggramik.product.service

import com.proggramik.product.domain.Product
import com.proggramik.product.domain.dto.*
import com.proggramik.product.repository.ProductRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun listProducts(): List<ProductDTO> {
        return productRepository.findAll().map { product ->
            ProductDTO(product.id!!, product.name, product.price.toDouble(), product.description)
        }
    }

    override fun findById(id: Long): ProductDTO? {
        return productRepository.findByIdOrNull(id)?.let { product ->
            ProductDTO(product.id!!, product.name, product.price.toDouble(), product.description)
        }
    }

    @Transactional
    override fun addProduct(request: AddProductRequestDTO): Mono<AddProductResponseDTO> {
        val product = productRepository.save(Product(request.name, BigDecimal(request.price)))
        return Mono.just(AddProductResponseDTO(product.id))
    }

    @Transactional
    override fun editProduct(id: Long, request: EditProductRequestDTO): Mono<Boolean> {
        productRepository.findByIdOrNull(id)?.let { product ->
            product.name = request.name ?: product.name
            product.price = request.price?.toBigDecimal() ?: product.price
            product.description = request.description ?: product.description

            return Mono.just(true)
        }

        return Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Product doesn't exist"))
    }

    @Transactional
    override fun removeProduct(request: RemoveProductRequestDTO): Mono<Boolean> {
        return try {
            productRepository.deleteById(request.productId)
            Mono.just(true)
        } catch (exception: EmptyResultDataAccessException) {
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, exception.message, exception))
        }
    }

    @Transactional
    override fun findAllById(request: FindAllRequestDTO): List<ProductDTO> {
        return productRepository.findAllById(request.productIds).map { product ->
            ProductDTO(product.id!!, product.name, product.price.toDouble(), product.description)
        }
    }
}
