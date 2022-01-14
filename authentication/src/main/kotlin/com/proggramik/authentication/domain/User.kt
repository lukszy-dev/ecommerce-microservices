package com.proggramik.authentication.domain

import javax.persistence.*

@Entity
class User(
    @Id
    @Column(name = "id", unique = true)
    val id: Long,
    @Enumerated(EnumType.STRING)
    val authorities: Authorities,
    @OneToOne(mappedBy = "user")
    val passwordCredential: PasswordCredential? = null
)
