package com.proggramik.user.controller

import com.proggramik.user.domain.dto.RegisterUserDTO
import com.proggramik.user.service.UserService
import com.proggramik.user.service.client.AuthenticationClient
import com.proggramik.user.service.dto.RegisterDTO
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
    private val authenticationClient: AuthenticationClient
) {
    @GetMapping("/{id}")
    fun user(@PathVariable id: Long): String? {
        return userService.findUser(id).get().email
    }

    @PostMapping("/register")
    fun register(@RequestBody user: RegisterUserDTO): Boolean {
        authenticationClient.register(RegisterDTO(user.email, user.password, 1))
        userService.register(user)
        return true
    }
}
