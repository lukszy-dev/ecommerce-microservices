package com.proggramik.user.service

import com.proggramik.user.domain.Product
import com.proggramik.user.domain.dto.AddProductDTO
import com.proggramik.user.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun listProducts(): List<Product> {
        return productRepository.findAll()
    }

    override fun findProduct(id: Long): Optional<Product> {
        return productRepository.findById(id)
    }

    override fun addProduct(product: AddProductDTO) {
        productRepository.save(Product(product.name))
    }
}
