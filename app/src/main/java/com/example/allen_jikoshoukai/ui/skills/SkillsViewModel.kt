package com.example.allen_jikoshoukai.ui.skills

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.allen_jikoshoukai.remote.model.Category
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseViewModel

class SkillsViewModel(application: Application) : BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    var skillIndex = MutableLiveData(-1)

    var categoryIndex = MutableLiveData(-1)
}