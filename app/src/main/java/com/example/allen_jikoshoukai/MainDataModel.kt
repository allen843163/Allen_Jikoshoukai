package com.example.allen_jikoshoukai

import com.example.allen_jikoshoukai.remote.ApiService
import com.example.allen_jikoshoukai.remote.RetrofitFactory
import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class MainDataModel {
    fun getIntroduction(): Observable<IntroductionRes> {
        return RetrofitFactory.get("https://us-central1-allenjikoshoukai.cloudfunctions.net/".toHttpUrlOrNull() as HttpUrl)
            .create(ApiService::class.java)
            .getIntroduction()
    }

}