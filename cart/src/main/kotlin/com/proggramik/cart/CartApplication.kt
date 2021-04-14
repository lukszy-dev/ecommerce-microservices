package com.proggramik.cart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CartApplication

fun main(args: Array<String>) {
	runApplication<CartApplication>(*args)
}
