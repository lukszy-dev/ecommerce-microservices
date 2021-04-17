package com.proggramik.cart.domain

import javax.persistence.*

@Entity
class OrderDetails(
    @OneToMany(mappedBy = "orderDetails", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val orderItems: MutableSet<OrderItem> = hashSetOf(),
    @OneToOne
    @JoinColumn(name = "cart_id")
    val cart: Cart? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
