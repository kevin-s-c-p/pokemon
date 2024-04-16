package mx.com.myapplication.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.com.web.NetworkResult
import retrofit2.Response

typealias ApiResponse<T> = Flow<NetworkResult<T>>

abstract class BaseRepository {

    protected fun <R> safeApiCall(apiCall: suspend () -> Response<R>) = flow<NetworkResult<R>> {
        try {
            emit(NetworkResult.Loading(true))

            val response = apiCall()

            if (response.isSuccessful) {
                emit(NetworkResult.Loading(false))
                emit(NetworkResult.Success(response.body()))
            } else {
                Log.d("ERROR REQUEST", response.message())
                emit(NetworkResult.Loading(false))
                emit(NetworkResult.Error(response.message(), response.code()))
            }
        } catch (exception: Exception) {
            emit(NetworkResult.Exception(exception))
        }
    }
}
