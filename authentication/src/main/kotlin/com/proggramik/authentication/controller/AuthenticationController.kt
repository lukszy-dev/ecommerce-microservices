package com.proggramik.authentication.controller

import com.proggramik.authentication.domain.dto.LoginDTO
import com.proggramik.authentication.domain.dto.RegisterDTO
import com.proggramik.authentication.domain.dto.ValidateTokenRequestDTO
import com.proggramik.authentication.domain.dto.ValidateTokenResponseDTO
import com.proggramik.authentication.service.UserCredentialService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userCredentialService: UserCredentialService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterDTO) {
        userCredentialService.register(request)
        print(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginDTO) {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
    }

    @GetMapping("/refresh")
    fun refresh(@RequestBody request: LoginDTO) {

    }

    @PostMapping("/validate-token")
    fun validateToken(@RequestBody request: ValidateTokenRequestDTO): ValidateTokenResponseDTO {
        return ValidateTokenResponseDTO(true)
    }
}
