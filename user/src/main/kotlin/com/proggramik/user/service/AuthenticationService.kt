package com.proggramik.user.service

import reactor.core.publisher.Mono
import java.util.*

interface AuthenticationService {
    fun registerCredentials(email: String, password: String, userId: UUID): Mono<Boolean>
}
