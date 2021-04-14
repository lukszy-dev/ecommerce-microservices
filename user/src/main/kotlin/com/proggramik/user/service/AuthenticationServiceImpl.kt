package com.proggramik.user.service

import com.proggramik.user.service.client.AuthenticationClient
import com.proggramik.user.service.client.dto.RegisterRequestDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authenticationClient: AuthenticationClient
) : AuthenticationService {
    override fun registerCredentials(email: String, password: String, userId: UUID): Mono<Boolean> {
        return authenticationClient.register(RegisterRequestDTO(email, password, userId)).map {
            response -> response.created
        }
    }
}
