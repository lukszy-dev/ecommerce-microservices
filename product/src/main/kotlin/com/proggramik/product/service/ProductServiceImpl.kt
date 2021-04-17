package com.proggramik.product.service

import com.proggramik.product.domain.Product
import com.proggramik.product.domain.dto.AddProductRequestDTO
import com.proggramik.product.domain.dto.ProductDTO
import com.proggramik.product.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

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

    override fun addProduct(request: AddProductRequestDTO) {
        productRepository.save(Product(request.name))
    }
}
