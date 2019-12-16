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
            override fun onViewItemSetting(holder: BaseViewHolder, view: View, item: Language, position: Int) {
                super.onViewItemSetting(holder, view, item, position)

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
        }

        binding.layoutMain.transitionToEnd()

        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
//        super.onCancel(dialog)
    }
}