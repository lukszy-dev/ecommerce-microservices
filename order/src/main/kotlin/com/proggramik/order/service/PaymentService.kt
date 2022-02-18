package com.proggramik.order.service

import com.proggramik.order.domain.dto.PaymentInitRequestDTO
import com.proggramik.order.domain.dto.PaymentInitResponseDTO
import reactor.core.publisher.Mono

interface PaymentService {
    fun init(request: PaymentInitRequestDTO): Mono<PaymentInitResponseDTO>
}