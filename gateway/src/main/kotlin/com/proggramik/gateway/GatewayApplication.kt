package com.proggramik.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class GatewayApplication {
    @Bean
    @LoadBalanced
    fun loadBalancedWebClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}
