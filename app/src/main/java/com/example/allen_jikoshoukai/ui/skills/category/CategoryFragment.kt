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
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CategoryFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by viewModel()

    lateinit var binding : ViewpagerSkillCategoryBinding

    var skillAdapter = object : MySimpleAdapter<Skill>(
        mutableListOf()
        , BR.skillData
        , R.layout.lv_item_skills
    ) {
        override fun onViewItemSetting(view: View, item: Skill, position: Int) {
            super.onViewItemSetting(view, item, position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = ViewpagerSkillCategoryBinding.inflate(inflater)

        binding.mainVM = getMainVM()

        binding.lvSkills.adapter = skillAdapter

        getMainVM().languageIndex.observe(this, Observer {
            when(it >= 0) {
                true -> {
                    getMainVM().getIntroductionRes.get()?.let { res->
                        skillAdapter.getData().clear()

                        skillAdapter.getData().addAll( res.Language[it].Skills)

                        skillAdapter.notifyDataSetChanged()
                    }?: let{
                        Toast.makeText(this.context, "No Skill Data", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        getSafeNavController()
            ?.navigate(R.id.nav_self_introduction)
    }
}