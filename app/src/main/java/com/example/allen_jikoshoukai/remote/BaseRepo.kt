package com.example.allen_jikoshoukai.remote

import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException
import okhttp3.ResponseBody.Companion.toResponseBody;
import org.mockito.internal.matchers.Null

open class BaseRepo{
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>) : Result<T> {
        return try {
            Timber.d("safeApiCall")

            var result = call()

            when(result.isSuccessful) {
                true -> {
                    if (result.body() != null) {

                            Result.success(result.body()!!)
                    }
                    else {
                            Result.failure(Throwable("Data is Null"))
                    }
                }
                false -> {
                    if(result.errorBody() != null) {
                        Result.failure(Throwable(result.errorBody()!!.string()))
                    }
                    else {
                        Result.failure(Throwable("未知的錯誤"))
                    }
                }
            }
        }
        catch (ex : UnknownHostException) {
            Result.failure(Throwable("請檢察網路是否正常"))
        }
        catch (e: Exception) {
            Result.failure(Throwable("未知的錯誤"))
        }
    }
}
