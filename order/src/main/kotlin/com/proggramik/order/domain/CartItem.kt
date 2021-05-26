package com.proggramik.order.domain

import javax.persistence.*

@Entity
class CartItem(
    @Column(name = "product_id")
    val productId: Long,
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    val cart: Cart,
    var quantity: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
