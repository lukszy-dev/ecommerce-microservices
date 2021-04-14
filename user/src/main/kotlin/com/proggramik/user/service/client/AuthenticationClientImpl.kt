package com.proggramik.user.service.client

import com.proggramik.user.service.client.dto.RegisterRequestDTO
import com.proggramik.user.service.client.dto.RegisterResponseDTO
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AuthenticationClientImpl(
    private val loadBalancedWebClientBuilder: WebClient.Builder
) : AuthenticationClient {
    override fun register(request: RegisterRequestDTO): Mono<RegisterResponseDTO> {
        return loadBalancedWebClientBuilder
            .build().post().uri("lb://authentication-service/register")
            .body(Mono.just(request), RegisterRequestDTO::class.java)
            .retrieve()
            .bodyToMono(RegisterResponseDTO::class.java)
    }
}
