package com.example.allen_jikoshoukai.remote

import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.*

interface ApiService {
    @Headers(value = ["Content-Type: application/json;charset=UTF-8"])
    @GET("api/introduction")
    fun getIntroduction() : Observable<IntroductionRes>

    @Headers(value = ["Content-Type: application/json;charset=UTF-8"])
    @GET("api/introduction")
    suspend fun getIntroduction2() : Response<IntroductionRes>
}