package com.abs.technology.data.remote

import com.abs.technology.data.remote.dto.LoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AbsApi {
    @POST
    suspend fun loginApi(
        @Body request: HashMap<String, String>
    ) : LoginDto
}