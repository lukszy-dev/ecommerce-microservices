package com.proggramik.order.domain

import javax.persistence.*

@Entity
class Cart(
    @Column(name = "user_id", unique = true)
    val userId: Long,
    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    var cartItems: MutableSet<CartItem> = hashSetOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
