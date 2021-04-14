package com.proggramik.authentication.service

import com.proggramik.authentication.domain.PasswordCredential
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JWTUser(
    private val passwordCredential: PasswordCredential
) : UserDetails {
    fun getPasswordCredential(): PasswordCredential {
        return passwordCredential
    }

    override fun getPassword(): String {
        return passwordCredential.password
    }

    override fun getUsername(): String {
        return passwordCredential.email
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            SimpleGrantedAuthority(passwordCredential.user.authorities.toString())
        )
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
