package com.proggramik.user.service

import com.proggramik.user.domain.User
import com.proggramik.user.domain.dto.RegisterUserDTO
import com.proggramik.user.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val authenticationService: AuthenticationService
): UserService {
    override fun findUser(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    override fun register(data: RegisterUserDTO): Mono<Boolean> {
        val user = userRepository.save(User(data.name, data.surname))
        if (user.id !== null) {
            return authenticationService.registerCredentials(data.email, data.password, user.id)
        }
        return Mono.just(false)
    }
}
