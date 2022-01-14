package com.proggramik.user.service

import com.proggramik.user.domain.User
import com.proggramik.user.domain.dto.RegisterUserDTO
import reactor.core.publisher.Mono

interface UserService {
    fun findUser(id: Long): User?
    fun register(data: RegisterUserDTO): Mono<Boolean>
}
