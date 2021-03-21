package com.proggramik.user.service

import com.proggramik.user.domain.User
import com.proggramik.user.domain.dto.RegisterUserDTO
import com.proggramik.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun findUser(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    override fun register(data: RegisterUserDTO) {
        val user = User(data.email, data.password, data.name, data.surname)
        userRepository.save(user)
    }
}
