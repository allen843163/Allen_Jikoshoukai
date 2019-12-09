package com.example.allen_jikoshoukai.ui.skills.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.BR
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentSkillsBinding
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillCategoryBinding
import com.example.allen_jikoshoukai.remote.model.Category
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CategoryFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by sharedViewModel()

    lateinit var binding : ViewpagerSkillCategoryBinding

    var categoryAdapter = object : MySimpleAdapter<Category>(
        mutableListOf()
        , BR.categoryData
        , R.layout.lv_item_skill_category
    ) {
        override fun onViewItemSetting(view: View, item: Category, position: Int) {
            super.onViewItemSetting(view, item, position)

            view.setOnClickListener {
                skillsViewModel.categoryIndex.value = position
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = ViewpagerSkillCategoryBinding.inflate(inflater)

        binding.lvCategory.adapter = categoryAdapter

        skillsViewModel.skillIndex.observe(this, Observer {
            Timber.d("observer".plus(it))

            categoryAdapter.getData().clear()

            getMainVM().languageIndex.value?.let { languageIndex ->

                getMainVM().getIntroductionRes.get()?.Language?.get(languageIndex)?.let { language ->

                    if(it >= 0) {
                        categoryAdapter.getData().addAll(language.Skills[it].Category)

                        binding.tvPath.setText(
                            language.Skills[it].Name
                            .plus(" > "))
                    }

                }
            }

            categoryAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        skillsViewModel.skillIndex.value = -1
    }
}