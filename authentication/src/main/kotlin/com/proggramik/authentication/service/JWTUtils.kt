package com.proggramik.authentication.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import org.springframework.beans.factory.annotation.Value
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

    companion object {
        const val CLAIM_KEY_ROLE = "role"
        const val CLAIM_KEY_EMAIL = "email"
    }

    @Throws(JWTCreationException::class)
    fun generateToken(userDetails: JWTUser): String {
        val passwordCredential = userDetails.getPasswordCredential()
        return JWT.create()
            .withExpiresAt(generateExpirationDate())
            .withSubject(passwordCredential.user.id.toString())
            .withClaim(CLAIM_KEY_EMAIL, passwordCredential.email)
            .withClaim(CLAIM_KEY_ROLE, userDetails.authorities.first().authority)
            .sign(algorithm)
    }

    @Throws(JWTVerificationException::class)
    fun validateToken(bearerToken: String): DecodedJWT {
        return verifier.verify(resolveToken(bearerToken))
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + expiration * 1000)
    }

    private fun resolveToken(bearerToken: String): String? {
        if (bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length)
        }
        return null
    }
}
