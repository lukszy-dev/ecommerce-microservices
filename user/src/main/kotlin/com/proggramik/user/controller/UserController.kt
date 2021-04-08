package com.proggramik.user.controller

import com.proggramik.user.domain.dto.RegisterUserDTO
import com.proggramik.user.security.CustomAuthenticationToken
import com.proggramik.user.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal
import java.util.*

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun user(principal: Principal): Mono<String> {
        val name = (principal as CustomAuthenticationToken).name
        val decodedSubject = String(Base64.getDecoder().decode(name))
        val subject = decodedSubject.split(':')
        return Mono.just(subject[1])
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterUserDTO): Mono<Boolean> {
        return userService.register(request)
    }
}
