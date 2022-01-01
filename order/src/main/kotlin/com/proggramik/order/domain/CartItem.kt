package com.proggramik.order.domain

import javax.persistence.*

@Entity
class CartItem(
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    val cart: Cart,
    var quantity: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
