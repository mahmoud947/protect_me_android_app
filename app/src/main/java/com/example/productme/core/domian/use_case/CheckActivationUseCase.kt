package com.example.productme.core.domian.use_case

import com.example.productme.core.data.remote.dto.Activation
import com.example.productme.core.domian.repository.CoreRepository
import com.example.productme.core.util.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CheckActivationUseCase(
    private val repository: CoreRepository
) {
    operator fun invoke():Flow<Resource<Activation>> = flow {
        try {
            emit(Resource.Loading<Activation>())
            val response=repository.checkActivation()
            emit(Resource.Success<Activation>(data = response))
        }catch (e:HttpException){
            emit(Resource.Error<Activation>(message = e.localizedMessage?:"An unexpected error occurred"))
        }catch (e:IOException){
            emit(Resource.Error<Activation>(message = "Couldn't reach server. Check your internet connection."))
        }
    }
}