package com.proggramik.user.service.client

import com.proggramik.user.service.client.dto.RegisterRequestDTO
import com.proggramik.user.service.client.dto.RegisterResponseDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

@ReactiveFeignClient(name = "authentication-service", url = "http://\${authentication-service.url:authentication-service}")
interface AuthenticationClient {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequestDTO): Mono<RegisterResponseDTO>
}
