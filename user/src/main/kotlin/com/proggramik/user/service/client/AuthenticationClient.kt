package com.proggramik.user.service.client

import com.proggramik.user.service.dto.RegisterDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "authentication-service")
interface AuthenticationClient {
    @PostMapping("register")
    fun register(@RequestBody request: RegisterDTO)
}
