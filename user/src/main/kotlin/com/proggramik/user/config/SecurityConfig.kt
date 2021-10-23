package com.proggramik.user.config

import com.proggramik.user.security.SecurityContextRepository
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

private val AUTH_WHITELIST = arrayOf("/register", "/v3/api-docs")

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig(
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
            .authorizeExchange().matchers(EndpointRequest.toAnyEndpoint()).permitAll().and()
            .authorizeExchange().pathMatchers(*AUTH_WHITELIST).permitAll()
            .anyExchange().authenticated().and()
            .securityContextRepository(securityContextRepository)
            .build()
    }
}
