package com.proggramik.authentication.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtils(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration}")
    private val expiration: Long,

    @Value("\${jwt.prefix}")
    private val prefix: String
) {
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    private val verifier: JWTVerifier = JWT.require(algorithm)
        .build()

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + expiration * 1000)
    }

    private fun resolveToken(bearerToken: String): String? {
        if (bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length)
        }
        return null
    }

    @Throws(JWTCreationException::class)
    fun generateToken(userDetails: UserDetails): String {
        return JWT.create()
            .withExpiresAt(generateExpirationDate())
            .withSubject(userDetails.username)
            .sign(algorithm)
    }

    @Throws(JWTVerificationException::class)
    fun validateToken(bearerToken: String): DecodedJWT {
        return verifier.verify(resolveToken(bearerToken))
    }
}
