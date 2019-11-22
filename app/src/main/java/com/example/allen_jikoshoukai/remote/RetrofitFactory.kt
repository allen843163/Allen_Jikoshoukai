package com.example.allen_jikoshoukai.remote

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory private constructor(){
    companion object {
        private val retrofitMap : HashMap<String, Retrofit> = HashMap()

        private val defaultRetrofit : Retrofit = let {
                Retrofit.Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://us-central1-allenjikoshoukai.cloudfunctions.net/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun get(url : HttpUrl) : Retrofit{
            if(!retrofitMap.containsKey(url.host)) {
                retrofitMap.put(url.host,
                    Retrofit.Builder()
                        .client(OkHttpClient.Builder().build())
                        .baseUrl(url)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                        .build()
                )
            }

            return retrofitMap[url.host]?.let {
                it
            }?:let  {
                defaultRetrofit
            }
        }
    }
}