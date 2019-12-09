package com.example.allen_jikoshoukai.ui.skills.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.BR
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillCategoryBinding
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillDetailBinding
import com.example.allen_jikoshoukai.remote.model.Category
import com.example.allen_jikoshoukai.remote.model.Detail
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class DetailFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by sharedViewModel()

    lateinit var binding : ViewpagerSkillDetailBinding

    var detailAdapter = object : MySimpleAdapter<Detail>(
        mutableListOf()
        , BR.detailData
        , R.layout.lv_item_skill_detail
    ) {
        override fun onViewItemSetting(view: View, item: Detail, position: Int) {
            super.onViewItemSetting(view, item, position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = ViewpagerSkillDetailBinding.inflate(inflater)

        binding.lvDetail.adapter = detailAdapter

        skillsViewModel.categoryIndex.observe(this, Observer {
            Timber.d("observer".plus(it))

            when(it >= 0) {
                true -> {
                    detailAdapter.getData().clear()

                    skillsViewModel.skillIndex.value?.let { skillIndex ->

                        getMainVM().languageIndex.value?.let { languageIndex ->

                            getMainVM().getIntroductionRes.get()?.Language?.get(languageIndex)?.Skills?.get(skillIndex)?.Category?.get(it)?.let {
                                    category ->
                                        detailAdapter.getData().addAll(category.Detail)

                                        binding.tvPath.setText(
                                            getMainVM().getIntroductionRes.get()?.Language?.get(languageIndex)?.Skills?.get(skillIndex)?.Name
                                                .plus(" > ")
                                                .plus(category.Name))
                            }
                        }
                    }

                    detailAdapter.notifyDataSetChanged()
                }
            }
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        skillsViewModel.categoryIndex.value = -1
    }

}