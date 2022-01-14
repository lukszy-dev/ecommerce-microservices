package com.proggramik.user.controller

import com.proggramik.user.domain.dto.RegisterUserDTO
import com.proggramik.user.security.CustomAuthenticationToken
import com.proggramik.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterUserDTO): Mono<Boolean> {
        return userService.register(request)
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun user(principal: Principal): Mono<String> {
        val userId = (principal as CustomAuthenticationToken).getUserID()
        userService.findUser(userId)?.let {
                user -> return Mono.just(user.name)
        }

        return Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
    }
}
