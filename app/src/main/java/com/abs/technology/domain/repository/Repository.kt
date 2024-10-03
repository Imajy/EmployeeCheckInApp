package com.abs.technology.domain.repository

import com.abs.technology.data.remote.dto.LoginDto

interface Repository {
    suspend fun loginApi(email: String): LoginDto
}