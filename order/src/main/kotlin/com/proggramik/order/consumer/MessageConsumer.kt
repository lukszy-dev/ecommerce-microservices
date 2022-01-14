package com.proggramik.order.consumer

import com.proggramik.order.domain.Product
import com.proggramik.order.repository.ProductRepository
import com.proggramik.product.api.message.AddProductMessage
import com.proggramik.product.api.message.EditProductMessage
import com.proggramik.product.api.message.RemoveProductMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.function.Consumer

@Component
class MessageConsumer(
    private val productRepository: ProductRepository
) {
    val logger: Logger = LoggerFactory.getLogger(MessageConsumer::class.java)

    @Bean
    fun addProduct(): Consumer<AddProductMessage> {
        return Consumer { p ->
            logger.info("Product to add, received - {}", p.id, p.toString())
            val product = productRepository.save(Product(p.name, BigDecimal(p.price), p.id))
            logger.info("Product [ID: {}] added", product.id)
        }
    }

    @Bean
    fun editProduct(): Consumer<EditProductMessage> {
        return Consumer { p ->
            logger.info("Product [ID: {}] to edit, received - {}", p.id, p.toString())
            productRepository.findByIdOrNull(p.id)?.let { product ->
                product.name = p.name ?: product.name
                product.price = p.price?.toBigDecimal() ?: product.price

                productRepository.save(product)
            }
            logger.info("Product [ID: {}] edited", p.id)
        }
    }

    @Bean
    fun removeProduct(): Consumer<RemoveProductMessage> {
        return Consumer { p ->
            logger.info("Product [ID: {}] to remove, received - {}", p.id, p.toString())
            productRepository.deleteById(p.id)
            logger.info("Product [ID: {}] removed", p.id)
        }
    }
}
