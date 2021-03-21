package com.proggramik.gateway

import com.proggramik.gateway.service.dto.ValidateTokenRequestDTO
import com.proggramik.gateway.service.dto.ValidateTokenResponseDTO
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class AuthenticationHeaderFilter : AbstractGatewayFilterFactory<AuthenticationHeaderFilter.Config>(Config::class.java) {
    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            WebClient
                .create().post()
                .uri("${config.authenticationServicePath}/validate-token")
                .body(Mono.just(ValidateTokenRequestDTO("TOKEN")), ValidateTokenRequestDTO::class.java)
                .retrieve()
                .bodyToMono(ValidateTokenResponseDTO::class.java)
                .flatMap { response ->
                    println(response.valid)
                    chain.filter(exchange)
                }
            // If you want to build a "pre" filter you need to manipulate the
            // request before calling chain.filter
//            val isTokenValid = authenticationClient.validateToken(ValidateTokenRequestDTO("TOKEN"))
//            println("TOKEN: $isTokenValid")
//            val builder: ServerHttpRequest.Builder = exchange.request.mutate()
            // chain.filter(exchange.mutate().request(request).build())
//            println("AUTHENTICATION FILTER!!!")
//            chain.filter(exchange)
        }
    }

    data class Config(val authenticationServicePath: String)
}
