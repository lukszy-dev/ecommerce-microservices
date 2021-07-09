package com.proggramik.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import reactivefeign.spring.config.EnableReactiveFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableReactiveFeignClients
@EnableFeignClients
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
