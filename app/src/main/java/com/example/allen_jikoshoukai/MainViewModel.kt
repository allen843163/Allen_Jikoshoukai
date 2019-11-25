package com.example.allen_jikoshoukai

import android.app.Application
import android.database.Observable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.remote.model.IntroductionRes
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application, private val dataModel: MainDataModel) : BaseViewModel(application) {
    val actionBarVisibilityController = MutableLiveData<Boolean>()

    var introductionData : ObservableField<IntroductionRes> = ObservableField()

    var languageIndex : ObservableInt = ObservableInt(0)

    fun remoteGetIntroduction(callback : GetIntroductionCallBack) {
        Timber.d("asdasdasd")

        dataModel.getIntroduction()
            .delay(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Timber.d(it.Language[0].Name)

                    introductionData.set(it)

                    callback.onSuccess()
                },
                {
                    it.printStackTrace()

                    callback.onFailure()
                }
            )
    }

    interface GetIntroductionCallBack{
        fun onSuccess()

        fun onFailure()
    }
}