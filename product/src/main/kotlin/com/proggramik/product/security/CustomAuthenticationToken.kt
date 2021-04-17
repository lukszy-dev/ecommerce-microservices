package com.proggramik.product.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import java.util.*

class CustomAuthenticationToken : UsernamePasswordAuthenticationToken {
    constructor() : super(null, null)
    constructor(subject: String?, authorities: List<GrantedAuthority>?, details: String? = null)
            : super(subject, null, authorities) {
        this.details = details
    }

    fun getUserUUID(): UUID {
        return UUID.fromString(this.name)
    }
}
