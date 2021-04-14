package com.proggramik.user.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
    val name: String = "",
    val surname: String = "",
    @Id
    @GeneratedValue
    @Column(unique = true, columnDefinition = "BINARY(16)")
    val id: UUID? = null
)
