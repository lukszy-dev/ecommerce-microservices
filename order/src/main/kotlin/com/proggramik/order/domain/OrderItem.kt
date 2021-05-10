package com.proggramik.order.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
class OrderItem(
    @Column(name = "product_name")
    val productName: String,
    @Column(name = "product_price")
    val productPrice: BigDecimal,
    @Column(name = "product_quantity")
    val productQuantity: Int,
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
