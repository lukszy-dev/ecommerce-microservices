package com.proggramik.authentication.service

import com.proggramik.authentication.repository.UserCredentialRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userCredentialRepository: UserCredentialRepository
): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val passwordCredential = userCredentialRepository.findByEmail(email)

        if (passwordCredential == null) {
            throw UsernameNotFoundException(String.format("No user found with email '%s'.", email))
        } else {
            return JWTUser(passwordCredential)
        }
    }
}
