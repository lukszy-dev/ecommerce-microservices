package com.proggramik.order.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAuthenticationToken : UsernamePasswordAuthenticationToken {
    constructor() : super(null, null)
    constructor(subject: String?, authorities: List<GrantedAuthority>?, details: String? = null)
            : super(subject, null, authorities) {
        this.details = details
    }

    fun getUserID(): Long {
        return this.name.toLong()
    }
}
