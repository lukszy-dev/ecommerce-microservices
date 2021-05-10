package com.proggramik.order.service.client

import com.proggramik.order.service.client.dto.ProductDTO
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class ProductClientImpl(
    private val loadBalancedWebClientBuilder: WebClient.Builder
) : ProductClient {
    override fun get(id: Long): Mono<ProductDTO> {
        return loadBalancedWebClientBuilder
            .build().get().uri( "lb://product-service/{id}", id)
            .retrieve()
            .bodyToMono(ProductDTO::class.java)
    }
}
