package com.proggramik.authentication

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @Value("\${spring.application.name}")
    private val appName: String? = null

    @GetMapping("/ping")
    fun ping(): String? {
        return appName
    }
}
