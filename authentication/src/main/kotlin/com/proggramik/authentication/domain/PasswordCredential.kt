package com.proggramik.authentication.domain

import java.util.*
import javax.persistence.*

@Entity
class PasswordCredential (
    val email: String,
    val password: String,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val authorities: Authorities,
    private var created: Date? = Date(),
    private var updated: Date? = Date(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    @PrePersist
    fun onCreate() {
        created = Date()
    }

    @PreUpdate
    fun onUpdate() {
        updated = Date()
    }
}
