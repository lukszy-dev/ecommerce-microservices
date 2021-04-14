package com.proggramik.cart.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAuthenticationToken : UsernamePasswordAuthenticationToken {
    constructor() : super(null, null)
    constructor(subject: String?, authorities: List<GrantedAuthority>?) : super(subject, null, authorities)
}
