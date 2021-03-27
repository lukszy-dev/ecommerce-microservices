package com.proggramik.gateway

import com.proggramik.gateway.service.dto.ValidateTokenRequestDTO
import com.proggramik.gateway.service.dto.ValidateTokenResponseDTO
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.http.server.reactive.ServerHttpResponse
import reactor.core.publisher.Mono

@Component
class AuthenticationGatewayFilterFactory(
    private val loadBalancedWebClientBuilder: WebClient.Builder
) : AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config>(Config::class.java) {

    data class Config(val tokenValidatorPath: String)

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            validateToken(config, exchange, chain)
        }
    }

    private fun onError(exchange: ServerWebExchange): Mono<Void?> {
        val response: ServerHttpResponse = exchange.response
        response.statusCode = HttpStatus.UNAUTHORIZED
        return response.setComplete()
    }

    private fun validateToken(config: Config, exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void?> {
        val authorizationHeader = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
            ?: return this.onError(exchange)

        return loadBalancedWebClientBuilder
            .build().post().uri(config.tokenValidatorPath)
            .body(Mono.just(ValidateTokenRequestDTO(authorizationHeader)), ValidateTokenRequestDTO::class.java)
            .retrieve()
            .bodyToMono(ValidateTokenResponseDTO::class.java)
            .flatMap { response ->
                if (response.valid) {
                    val builder = exchange.request.mutate()
                    builder.header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    val request = builder.build()
                    chain.filter(exchange.mutate().request(request).build())
                } else {
                    onError(exchange)
                }
            }
    }
}
