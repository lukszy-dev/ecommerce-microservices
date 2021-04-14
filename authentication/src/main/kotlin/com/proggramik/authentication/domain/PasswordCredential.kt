package com.proggramik.authentication.domain

import java.util.*
import javax.persistence.*

@Entity
class PasswordCredential (
    @Column(unique = true)
    val email: String,
    val password: String,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val user: User,
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
