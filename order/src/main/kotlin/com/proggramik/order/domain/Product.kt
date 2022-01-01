package com.proggramik.order.domain

import java.math.BigDecimal
import javax.persistence.*

/**
 * Cached product data
 */
@Entity
class Product(
    var name: String,
    @Column(precision = 10, scale = 2)
    var price: BigDecimal,
    @Id
    @Column(name = "id", unique = true)
    val id: Long
)
