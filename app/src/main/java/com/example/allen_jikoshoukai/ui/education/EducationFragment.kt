package com.example.allen_jikoshoukai.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentEducationBinding
import com.example.allen_jikoshoukai.databinding.FragmentLaunchBinding
import com.example.allen_jikoshoukai.remote.model.Education
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import org.koin.android.ext.android.get

class EducationFragment : BaseFragment() {
    private lateinit var binding : FragmentEducationBinding

    private var educationAdapter = object : MySimpleAdapter<Education>(
        mutableListOf(),
        BR.educationData,
        R.layout.lv_item_education
    ) {

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEducationBinding.inflate(inflater)

        binding.lvEducation.adapter = educationAdapter

        getMainVM().languageIndex.observe(this, Observer {
            educationAdapter.getData().clear()

            getMainVM().getIntroductionRes.get()?.Language?.get(it)?.Education?.let { educationList ->
                educationAdapter.getData().addAll(educationList)
            }

            educationAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        getSafeNavController()?.navigate(R.id.nav_self_introduction)
    }
}