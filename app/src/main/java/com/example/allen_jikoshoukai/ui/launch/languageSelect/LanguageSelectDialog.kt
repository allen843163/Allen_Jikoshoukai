package com.example.allen_jikoshoukai.ui.launch.languageSelect

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.Observable
import androidx.databinding.library.baseAdapters.BR

import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.DialogLanguageSelectBinding
import com.example.allen_jikoshoukai.remote.model.Language
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseDialog
import kotlinx.coroutines.delay
import org.koin.android.ext.android.bind
import timber.log.Timber

class LanguageSelectDialog : BaseDialog() {
    lateinit var binding : DialogLanguageSelectBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false

        setStyle(STYLE_NORMAL, R.style.Base_DialogFragment_Style)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLanguageSelectBinding.inflate(inflater)

        binding.mainVM = getMainVM()

        var languageAdapter = object : MySimpleAdapter<Language>(
            mutableListOf()
            , BR.languageData
            ,R.layout.lv_item_language) {
            override fun onViewItemSetting(view: View, item: Language, position: Int) {
                super.onViewItemSetting(view, item, position)

                view.setOnClickListener {
                    binding.mainVM?.setLanguageIndex(position)

                    getSafeNavController()?.navigateUp()
                }
            }
        }

        binding.lvLanguage.adapter = languageAdapter

        languageAdapter.getData().clear()

        getMainVM().getIntroductionRes.get()?.Language?.let {
            Timber.d("languageAdapter init :".plus("size").plus(it.size))

            languageAdapter.getData().addAll(it)
            languageAdapter.getData().addAll(it)
            languageAdapter.getData().addAll(it)
        }

        binding.layoutMain.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                Timber.d("aaaa".plus(p1))
                Timber.d("aaaa".plus(p2))
                Timber.d("aaaa".plus(p3))
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }

        })
//        binding.layoutContent.transitionToEnd()
        binding.layoutMain.transitionToEnd()

        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
//        super.onCancel(dialog)
    }
}