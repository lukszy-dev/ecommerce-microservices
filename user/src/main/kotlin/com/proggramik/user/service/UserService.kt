package com.proggramik.user.service

import com.proggramik.user.domain.User
import com.proggramik.user.domain.dto.RegisterUserDTO
import reactor.core.publisher.Mono
import java.util.*

interface UserService {
    fun findUser(id: UUID): User?
    fun register(data: RegisterUserDTO): Mono<Boolean>
}
