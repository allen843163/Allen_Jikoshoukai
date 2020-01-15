package com.example.allen_jikoshoukai

import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.allen_jikoshoukai.remote.BaseRepo
import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import com.example.allen_jikoshoukai.remote.model.Language
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import okhttp3.internal.wait
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application, private val dataModel: MainDataModel) : BaseViewModel(application) {
    val actionBarVisibilityController = MutableLiveData<Boolean>()

    var getIntroductionRes : ObservableField<IntroductionRes> = ObservableField()

    var languageIndex : MutableLiveData<Int> = MutableLiveData(-1)

    fun init() {
        getIntroductionRes = ObservableField()

        languageIndex = MutableLiveData(-1)
    }

    fun setLanguageIndex(index : Int) {
        languageIndex.value = index
    }


    fun remoteGetIntroduction() : MutableLiveData<Result<IntroductionRes>>{
        if(BuildConfig.isCoroutineMode) {
            /**
             * Kotlin Coroutine作法
             */
            val result : MutableLiveData<Result<IntroductionRes>> = MutableLiveData()

            GlobalScope.launch {
                delay(2000)

                Timber.d("Get Introduction by Coroutine")

                val response : Result<IntroductionRes> = dataModel.getIntroduction2()

                if(response.isSuccess) {
                    getIntroductionRes.set(response.getOrNull()!!)

                    result.postValue(response)
                }
                else {
                    result.postValue(response)
                }
            }.start()

            return result
        }
        else {
            /**
             * RxJava作法
             */
            val result : MutableLiveData<Result<IntroductionRes>> = MutableLiveData()

            Timber.d("Get Introduction by Rxjava")

            dataModel.getIntroduction()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Timber.d(it.Language[0].Name)

                        getIntroductionRes.set(it)

                        result.value = Result.success(it)
                    },
                    {
                        it.printStackTrace()

                        result.value = Result.failure(it)
                    }
                )

            return result
        }
    }
}