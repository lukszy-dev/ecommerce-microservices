package com.proggramik.cart.domain

import java.util.*
import javax.persistence.*

@Entity
class Cart(
    @Column(name = "user_id", unique = true, columnDefinition = "BINARY(16)")
    val userId: UUID,
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    var cartItems: Set<CartItem>? = emptySet(),
    @OneToOne(mappedBy = "cart")
    val order: Order? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
