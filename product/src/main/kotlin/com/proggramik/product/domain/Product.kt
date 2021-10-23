package com.proggramik.product.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product (
    var name: String,
    @Column(precision = 10, scale = 2)
    var price: BigDecimal,
    var description: String? = "",
//    @ManyToOne
//    val category: Category? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
