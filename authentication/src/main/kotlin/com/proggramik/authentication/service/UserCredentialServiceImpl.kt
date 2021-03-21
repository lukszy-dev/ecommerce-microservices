package com.proggramik.authentication.service

import com.proggramik.authentication.domain.Authorities
import com.proggramik.authentication.domain.PasswordCredential
import com.proggramik.authentication.domain.dto.RegisterDTO
import com.proggramik.authentication.repository.UserCredentialRepository
import org.springframework.stereotype.Service

@Service
class UserCredentialServiceImpl(
    private val userCredentialRepository: UserCredentialRepository
): UserCredentialService {
    override fun register(registerDTO: RegisterDTO): PasswordCredential? {
        return userCredentialRepository.save(
            PasswordCredential(
                registerDTO.email,
                registerDTO.password,
                registerDTO.userId,
                Authorities.USER
            )
        )
    }
}
