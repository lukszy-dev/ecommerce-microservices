package com.proggramik.cart.domain

import java.util.*
import javax.persistence.*

@Entity
class Cart(
    @Column(name = "user_id", unique = true, columnDefinition = "BINARY(16)")
    val userId: UUID,
    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var cartItems: MutableSet<CartItem>? = hashSetOf(),
    @OneToOne(mappedBy = "cart")
    val orderDetails: OrderDetails? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
