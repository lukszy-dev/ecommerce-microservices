package com.proggramik.authentication.service

import com.proggramik.authentication.repository.UserCredentialRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Service
class UserDetailsServiceImpl(
    private val userCredentialRepository: UserCredentialRepository
) : ReactiveUserDetailsService {
    override fun findByUsername(email: String): Mono<UserDetails> {
        val passwordCredential = userCredentialRepository.findByEmail(email)

        return if (passwordCredential == null) {
            Mono.error(
                ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    String.format("No user found with email '%s'", email)
                )
            )
        } else {
            Mono.just(JWTUser(passwordCredential))
        }
    }
}
