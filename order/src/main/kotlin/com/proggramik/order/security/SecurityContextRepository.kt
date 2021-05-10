package com.proggramik.order.security

import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import javax.transaction.NotSupportedException

@Component
class SecurityContextRepository(
    private val tokenAuthenticationService: TokenAuthenticationService
) : ServerSecurityContextRepository {
    companion object {
        const val ROLE_PREFIX = "ROLE_"
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        return try {
            val authHeader = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
            val decodedToken = tokenAuthenticationService.verify(authHeader)

            val authentication = if (decodedToken == null) {
                CustomAuthenticationToken()
            } else {
                val role = ROLE_PREFIX + tokenAuthenticationService.getRole(decodedToken)
                CustomAuthenticationToken(decodedToken.subject, listOf(SimpleGrantedAuthority(role)))
            }

            Mono.just(SecurityContextImpl(authentication))
        } catch (exception: JWTVerificationException) {
            Mono.error(ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.message, exception))
        }
    }

    override fun save(exchange: ServerWebExchange, context: SecurityContext): Mono<Void> {
        throw NotSupportedException()
    }
}
