package com.proggramik.order.controller

import com.proggramik.order.domain.dto.PaymentInitRequestDTO
import com.proggramik.order.domain.dto.PaymentInitResponseDTO
import com.proggramik.order.service.PaymentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/payment")
class PaymentController(
    val paymentService: PaymentService
) {
    @PostMapping("/init")
    @PreAuthorize("hasRole('USER')")
    fun init(@RequestBody request: PaymentInitRequestDTO): Mono<PaymentInitResponseDTO> {
        return paymentService.init(request)
    }

    @GetMapping("/success")
    fun success() {

    }

    @GetMapping("/cancel")
    fun cancel() {

    }
}
