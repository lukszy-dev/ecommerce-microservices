package com.proggramik.user

import com.proggramik.user.domain.Product
import com.proggramik.user.repository.ProductRepository
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
