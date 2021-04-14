package com.proggramik.authentication.domain

import java.util.*
import javax.persistence.*

@Entity
class User(
    @Id
    @Column(name = "id", unique = true, columnDefinition = "BINARY(16)")
    val id: UUID,
    @Enumerated(EnumType.STRING)
    val authorities: Authorities,
    @OneToOne(mappedBy = "user")
    val passwordCredential: PasswordCredential? = null
)
