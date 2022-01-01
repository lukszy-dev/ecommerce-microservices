package com.proggramik.order.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
class OrderItem(
    @Column(name = "product_quantity")
    val quantity: Int,
    @Column(name = "product_price")
    val subtotal: BigDecimal,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id")
    val product: Product,
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
