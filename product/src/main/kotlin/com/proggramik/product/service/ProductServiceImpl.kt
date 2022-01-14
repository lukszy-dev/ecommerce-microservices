package com.proggramik.product.service

import com.proggramik.product.api.Bindings
import com.proggramik.product.api.dto.ProductDTO
import com.proggramik.product.api.message.AddProductMessage
import com.proggramik.product.api.message.EditProductMessage
import com.proggramik.product.api.message.RemoveProductMessage
import com.proggramik.product.domain.Product
import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.AddProductResponseDTO
import com.proggramik.product.domain.dto.EditProductRequestDTO
import com.proggramik.product.domain.dto.RemoveProductRequestDTO
import com.proggramik.product.repository.ProductRepository
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val streamBridge: StreamBridge
) : ProductService {
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
        val product = productRepository.save(Product(request.name, BigDecimal(request.price), request.description))

        if (product.id !== null) {
            val message = AddProductMessage(product.id, request.name, request.price)
            streamBridge.send(Bindings.ADD_PRODUCT_OUTPUT, message)
        }

        return Mono.just(AddProductResponseDTO(product.id))
    }

    @Transactional
    override fun editProduct(id: Long, request: EditProductRequestDTO): Mono<Boolean> {
        productRepository.findByIdOrNull(id)?.let { product ->
            product.name = request.name ?: product.name
            product.price = request.price?.toBigDecimal() ?: product.price
            product.description = request.description ?: product.description

            if (product.id !== null) {
                val message = EditProductMessage(id, request.name, request.price)
                streamBridge.send(Bindings.EDIT_PRODUCT_OUTPUT, message)
            }

            return Mono.just(true)
        }

        return Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "Product doesn't exist"))
    }

    @Transactional
    override fun removeProduct(request: RemoveProductRequestDTO): Mono<Boolean> {
        return try {
            productRepository.removeById(request.productId)

            val message = RemoveProductMessage(request.productId)
            streamBridge.send(Bindings.REMOVE_PRODUCT_OUTPUT, message)

            Mono.just(true)
        } catch (exception: EmptyResultDataAccessException) {
            Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, exception.message, exception))
        }
    }
}
