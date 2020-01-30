package com.example.allen_jikoshoukai.ui.selfintroduction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.navigateUp
import com.example.allen_jikoshoukai.databinding.FragmentSelfIntroductionBinding
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment

class SelfIntroductionFragment : BaseFragment() {
    private lateinit var binding : FragmentSelfIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelfIntroductionBinding.inflate(inflater)

        getMainVM().languageIndex.observe(this, Observer { index ->

            getMainVM().getIntroductionRes.get()?.Language?.get(index)?.let {
                binding.txtSelfIntroduction.text = it.SelfIntroduction
            }
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        return
    }
}