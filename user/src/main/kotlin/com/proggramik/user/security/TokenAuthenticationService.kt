package com.proggramik.user.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TokenAuthenticationService(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.prefix}")
    private val prefix: String
) {
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    private val verifier: JWTVerifier = JWT.require(algorithm)
        .build()

    private fun resolveToken(bearerToken: String?): String? {
        if (bearerToken !== null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length)
        }
        return null
    }

    @Throws(JWTVerificationException::class)
    fun verify(token: String?): DecodedJWT? {
        return if (token !== null) verifier.verify(resolveToken(token)) else null
    }
}
