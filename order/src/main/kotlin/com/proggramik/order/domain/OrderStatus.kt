package com.proggramik.order.domain

enum class OrderStatus {
    PENDING,
    IN_PROGRESS,
    AWAITING_PAYMENT,
    PAYMENT_RECEIVED,
    AWAITING_SHIPMENT,
    SHIPPED,
    REFUNDED,
    CANCELED,
    COMPLETED
}