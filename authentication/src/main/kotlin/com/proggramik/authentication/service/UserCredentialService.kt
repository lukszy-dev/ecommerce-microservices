package com.proggramik.authentication.service

import com.proggramik.authentication.domain.PasswordCredential
import com.proggramik.authentication.domain.dto.RegisterRequestDTO

interface UserCredentialService {
    fun register(registerRequestDTO: RegisterRequestDTO): PasswordCredential?
}
