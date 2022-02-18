package com.proggramik.order.service

import com.proggramik.order.domain.dto.PaymentInitRequestDTO
import com.proggramik.order.domain.dto.PaymentInitResponseDTO
import com.proggramik.order.repository.OrderRepository
import com.stripe.Stripe
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.math.BigDecimal
import javax.annotation.PostConstruct

@Service
class PaymentServiceImpl(
    val orderRepository: OrderRepository
) : PaymentService {
    @PostConstruct
    fun postConstruct() {
        Stripe.apiKey = ""
    }

    override fun init(request: PaymentInitRequestDTO): Mono<PaymentInitResponseDTO> {
        orderRepository.getById(request.orderId).let { order ->
            val params = PaymentIntentCreateParams.builder()
                .setAmount(order.calculateOrderAmount().multiply(BigDecimal(100)).toLong())
                .setCurrency("eur")
                .setAutomaticPaymentMethods(
                    PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                        .setEnabled(true)
                        .build()
                )
                .build()

            val paymentIntent = PaymentIntent.create(params)

            return Mono.just(PaymentInitResponseDTO(paymentIntent.clientSecret))
        }
    }
}