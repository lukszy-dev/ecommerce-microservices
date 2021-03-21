package com.proggramik.authentication.domain

enum class Authorities(private val authority: String) {
    USER("USER"), ADMIN("ADMIN");

    override fun toString(): String {
        return authority
    }
}
