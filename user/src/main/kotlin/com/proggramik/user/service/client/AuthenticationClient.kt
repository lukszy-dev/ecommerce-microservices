package com.proggramik.user.service.client

import com.proggramik.user.service.client.dto.RegisterRequestDTO
import com.proggramik.user.service.client.dto.RegisterResponseDTO
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono

interface AuthenticationClient {
    fun register(@RequestBody request: RegisterRequestDTO): Mono<RegisterResponseDTO>
}
