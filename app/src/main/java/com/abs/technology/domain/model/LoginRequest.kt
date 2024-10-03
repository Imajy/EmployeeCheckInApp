package com.abs.technology.domain.model

data class LoginRequest(
    val chatId : String = "",
    val userId : String = "",
    val anotherUserId : String = "",
)
