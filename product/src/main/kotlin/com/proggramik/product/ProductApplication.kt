package com.proggramik.product

import com.proggramik.product.domain.Product
import com.proggramik.product.repository.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class ProductApplication {
	@Bean
	fun demo(productRepository: ProductRepository): CommandLineRunner? {
		return CommandLineRunner {
			productRepository.save(Product("Red Rose"))
			productRepository.save(Product("White Rose"))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<ProductApplication>(*args)
}
