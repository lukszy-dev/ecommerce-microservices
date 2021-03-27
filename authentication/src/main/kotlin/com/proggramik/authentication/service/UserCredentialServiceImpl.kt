package com.proggramik.authentication.service

import com.proggramik.authentication.domain.Authorities
import com.proggramik.authentication.domain.PasswordCredential
import com.proggramik.authentication.domain.dto.RegisterRequestDTO
import com.proggramik.authentication.repository.UserCredentialRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserCredentialServiceImpl(
    private val userCredentialRepository: UserCredentialRepository,
    private val passwordEncoder: PasswordEncoder
) : UserCredentialService {
    override fun register(registerRequestDTO: RegisterRequestDTO): PasswordCredential? {
        return userCredentialRepository.save(
            PasswordCredential(
                registerRequestDTO.email,
                passwordEncoder.encode(registerRequestDTO.password),
                registerRequestDTO.userId,
                Authorities.USER
            )
        )
    }
}
