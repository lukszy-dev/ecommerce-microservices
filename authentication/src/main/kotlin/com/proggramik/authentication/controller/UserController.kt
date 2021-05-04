package com.proggramik.authentication.controller

import com.proggramik.authentication.domain.dto.LoginRequestDTO
import com.proggramik.authentication.domain.dto.LoginResponseDTO
import com.proggramik.authentication.domain.dto.RegisterRequestDTO
import com.proggramik.authentication.domain.dto.RegisterResponseDTO
import com.proggramik.authentication.service.JWTUser
import com.proggramik.authentication.service.JWTUtils
import com.proggramik.authentication.service.UserCredentialService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
class UserController(
    private val authenticationManager: ReactiveAuthenticationManager,
    private val userCredentialService: UserCredentialService,
    private val jwtUtils: JWTUtils
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequestDTO): Mono<RegisterResponseDTO> {
        return userCredentialService.register(request)
            .map { passwordCredential -> RegisterResponseDTO(passwordCredential != null) }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): Mono<LoginResponseDTO> {
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        ).onErrorMap { exception ->
            ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.message, exception)
        }.map { authentication ->
            val userDetails = authentication.principal as JWTUser
            val token = jwtUtils.generateToken(userDetails)
            LoginResponseDTO(token)
        }
    }
}
