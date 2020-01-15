package com.example.allen_jikoshoukai

import com.example.allen_jikoshoukai.remote.ApiService
import com.example.allen_jikoshoukai.remote.BaseRepo
import com.example.allen_jikoshoukai.remote.RetrofitFactory
import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import retrofit2.Response

class MainDataModel : BaseRepo() {
    fun getIntroduction(): Observable<IntroductionRes> {
        return RetrofitFactory.get("https://us-central1-allenjikoshoukai.cloudfunctions.net/".toHttpUrlOrNull() as HttpUrl)
            .create(ApiService::class.java)
            .getIntroduction()
    }
    suspend fun getIntroduction2(): Result<IntroductionRes> {
        return safeApiCall(
            call = {RetrofitFactory.get("https://us-central1-allenjikoshoukai.cloudfunctions.net/".toHttpUrlOrNull() as HttpUrl)
                .create(ApiService::class.java)
                .getIntroduction2()})

    }
}