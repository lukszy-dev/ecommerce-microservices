package com.proggramik.authentication.service

import com.proggramik.authentication.domain.PasswordCredential
import com.proggramik.authentication.domain.dto.RegisterDTO

interface UserCredentialService {
    fun register(registerDTO: RegisterDTO): PasswordCredential?
}
