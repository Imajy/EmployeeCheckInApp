package com.abs.technology.data.repository

import com.abs.technology.data.remote.AbsApi
import com.abs.technology.data.remote.dto.LoginDto
import com.abs.technology.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: AbsApi
) : Repository {
    override suspend fun loginApi(email: String): LoginDto {
        val hash = HashMap<String, String>()
        hash["email"] = email

        return api.loginApi(hash)
    }

}