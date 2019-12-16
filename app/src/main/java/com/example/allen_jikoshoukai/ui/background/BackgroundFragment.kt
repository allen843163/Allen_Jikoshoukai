package com.example.allen_jikoshoukai.ui.background

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentBackgroundBinding
import com.example.allen_jikoshoukai.databinding.FragmentEducationBinding
import com.example.allen_jikoshoukai.remote.model.Background
import com.example.allen_jikoshoukai.remote.model.Education
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import org.koin.android.ext.android.bind

class BackgroundFragment : BaseFragment() {
    private lateinit var binding : FragmentBackgroundBinding

    private var backgroundAdapter = object : MySimpleAdapter<Background>(
        mutableListOf(),
        BR.backgroundData,
        R.layout.lv_item_background
    ) {
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackgroundBinding.inflate(inflater)

        binding.lvBackground.adapter = backgroundAdapter

        getMainVM().languageIndex.observe(this, Observer {
            backgroundAdapter.getData().clear()

            getMainVM().getIntroductionRes.get()?.Language?.get(it)?.Background?.let { backgroundList ->
                backgroundAdapter.getData().addAll(backgroundList)
            }

            backgroundAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        getSafeNavController()?.navigate(R.id.nav_self_introduction)
    }

}