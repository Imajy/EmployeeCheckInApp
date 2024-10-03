package com.abs.technology.domain.use_case

import com.abs.technology.common.Resource
import com.abs.technology.data.remote.dto.LoginDto
import com.abs.technology.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(user_id:String): Flow<Resource<LoginDto>> = flow {
        try {
            emit(Resource.Loading())
            val likes = repository.loginApi(user_id)
            emit(Resource.Success(likes))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Can't reach server, check internet connection!"))
        }
    }
}
