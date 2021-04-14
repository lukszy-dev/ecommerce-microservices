package com.proggramik.cart.domain

import javax.persistence.*

@Entity
class Order(
    @OneToMany(mappedBy = "order")
    val items: Set<Item> = emptySet(),
    @OneToOne
    @JoinColumn(name = "cart_id")
    val cart: Cart? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
