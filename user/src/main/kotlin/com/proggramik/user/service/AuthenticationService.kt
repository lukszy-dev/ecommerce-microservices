package com.proggramik.user.service

import reactor.core.publisher.Mono

interface AuthenticationService {
    fun registerCredentials(email: String, password: String, userId: Long): Mono<Boolean>
}
