package com.proggramik.order.domain

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val orderItems: MutableSet<OrderItem> = hashSetOf(),
    @Column(name = "user_id", unique = true)
    val userId: Long,
    val status: String? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
