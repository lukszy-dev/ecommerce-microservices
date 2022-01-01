package com.proggramik.order.domain

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val orderItems: MutableSet<OrderItem> = hashSetOf(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
