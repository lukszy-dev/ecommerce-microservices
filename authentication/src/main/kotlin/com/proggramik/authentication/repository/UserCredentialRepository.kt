package com.proggramik.authentication.repository

import com.proggramik.authentication.domain.PasswordCredential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCredentialRepository : JpaRepository<PasswordCredential, Long> {
    fun findByEmail(email: String): PasswordCredential?
}
