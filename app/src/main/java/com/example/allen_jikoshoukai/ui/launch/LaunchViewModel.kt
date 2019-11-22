package com.example.allen_jikoshoukai.ui.launch

import android.app.Application
import com.example.allen_jikoshoukai.App
import com.example.allen_jikoshoukai.MainDataModel
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LaunchViewModel(application: Application, private val dataModel: MainDataModel) : BaseViewModel(application) {
    fun remoteGetIntroduction() {
        Timber.d("asdasdasd")

        dataModel.getIntroduction()
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Timber.d(it.Language[0].Name)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}