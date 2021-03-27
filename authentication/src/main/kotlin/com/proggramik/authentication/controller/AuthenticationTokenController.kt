package com.proggramik.authentication.controller

import com.auth0.jwt.exceptions.JWTVerificationException
import com.proggramik.authentication.domain.dto.LoginRequestDTO
import com.proggramik.authentication.domain.dto.ValidateTokenRequestDTO
import com.proggramik.authentication.domain.dto.ValidateTokenResponseDTO
import com.proggramik.authentication.service.JWTUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/token")
class AuthenticationTokenController(
    private val jwtUtils: JWTUtils
) {
    @GetMapping("/refresh")
    fun refresh(@RequestBody request: LoginRequestDTO) {
        TODO("Not yet implemented")
    }

    @PostMapping("/validate")
    fun validateToken(@RequestBody request: ValidateTokenRequestDTO): ValidateTokenResponseDTO {
        try {
            jwtUtils.validateToken(request.token)
        } catch (exception: JWTVerificationException) {
            return ValidateTokenResponseDTO(false, exception.message)
        }

        return ValidateTokenResponseDTO(true)
    }
}
