package com.proggramik.authentication.service

import com.proggramik.authentication.domain.PasswordCredential
import com.proggramik.authentication.domain.dto.RegisterRequestDTO
import reactor.core.publisher.Mono

interface UserCredentialService {
    fun register(registerRequestDTO: RegisterRequestDTO): Mono<PasswordCredential>
}
