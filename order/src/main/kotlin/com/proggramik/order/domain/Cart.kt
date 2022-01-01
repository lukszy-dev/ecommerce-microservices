package com.proggramik.order.domain

import java.util.*
import javax.persistence.*

@Entity
class Cart(
    @Column(name = "user_id", unique = true, columnDefinition = "BINARY(16)")
    val userId: UUID,
    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    var cartItems: MutableSet<CartItem> = hashSetOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
