package com.proggramik.user.service

import com.proggramik.user.domain.User
import com.proggramik.user.domain.dto.RegisterUserDTO
import java.util.*

interface UserService {
    fun findUser(id: Long): Optional<User>
    fun register(data: RegisterUserDTO)
}
