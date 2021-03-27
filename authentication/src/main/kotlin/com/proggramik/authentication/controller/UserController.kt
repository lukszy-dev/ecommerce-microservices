package com.proggramik.authentication.controller

import com.proggramik.authentication.domain.dto.*
import com.proggramik.authentication.service.JWTUtils
import com.proggramik.authentication.service.JWTUser
import com.proggramik.authentication.service.UserCredentialService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
class UserController(
    private val authenticationManager: AuthenticationManager,
    private val userCredentialService: UserCredentialService,
    private val jwtUtils: JWTUtils
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequestDTO): RegisterResponseDTO {
        val passwordCredential = userCredentialService.register(request)
        return RegisterResponseDTO(passwordCredential != null)
    }

    @PostMapping("/login")
    @Throws(AuthenticationException::class)
    fun login(@RequestBody request: LoginRequestDTO): LoginResponseDTO {
        val userDetails: JWTUser = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        ).principal as JWTUser

        val token = jwtUtils.generateToken(userDetails)

        return LoginResponseDTO(token)
    }
}
