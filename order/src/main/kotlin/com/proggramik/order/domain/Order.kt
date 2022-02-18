package com.proggramik.order.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    var orderItems: MutableSet<OrderItem> = hashSetOf(),
    @Column(name = "user_id", unique = true)
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val status: OrderStatus = OrderStatus.PENDING,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    fun calculateOrderAmount(): BigDecimal {
        return orderItems.map { it.subtotal }.sumOf { it }
    }
}
