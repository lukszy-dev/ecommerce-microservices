package com.proggramik.cart.config

import com.proggramik.cart.security.AuthenticationManager
import com.proggramik.cart.security.SecurityContextRepository
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig(
    private val authenticationManager: AuthenticationManager,
    private val securityContextRepository: SecurityContextRepository
) {
    @Bean
    fun securityWebFilterChain(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
        return httpSecurity
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .httpBasic().disable()
            .authorizeExchange()
                .matchers(EndpointRequest.toAnyEndpoint()).permitAll().and()
            .authorizeExchange()
                .anyExchange().authenticated().and()
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .build()
    }
}
