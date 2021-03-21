package com.proggramik.authentication.service

import com.proggramik.authentication.domain.PasswordCredential
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser(
    private val passwordCredential: PasswordCredential
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(passwordCredential.authorities.toString()))
    }

    override fun getPassword(): String {
        return passwordCredential.password
    }

    override fun getUsername(): String {
        return passwordCredential.email
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
