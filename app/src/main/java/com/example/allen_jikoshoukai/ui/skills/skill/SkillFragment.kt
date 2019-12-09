package com.example.allen_jikoshoukai.ui.skills.skill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.allen_jikoshoukai.BR
import com.example.allen_jikoshoukai.MainViewModel
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillBinding
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillCategoryBinding
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SkillFragment : BaseFragment(){
    private val skillsViewModel : SkillsViewModel by sharedViewModel()

    lateinit var binding : ViewpagerSkillBinding

    var skillAdapter = object : MySimpleAdapter<Skill>(
        mutableListOf()
        , BR.skillData
        , R.layout.lv_item_skills
    ) {
        override fun onViewItemSetting(view: View, item: Skill, position: Int) {
            super.onViewItemSetting(view, item, position)

            view.setOnClickListener {
                skillsViewModel.skillIndex.value = position

                Timber.d("item click : ".plus(skillsViewModel.skillIndex.value))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView")

        binding = ViewpagerSkillBinding.inflate(inflater)

        binding.lvSkills.adapter = skillAdapter

        getMainVM().languageIndex.observe(this, Observer { it ->

            getMainVM().getIntroductionRes.get()?.let { introduction ->
                Timber.d("observe")

                skillAdapter.getData().clear()

                skillAdapter.getData().addAll(introduction.Language[it].Skills as MutableList<Skill>)

                skillAdapter.notifyDataSetChanged()
            }

        })

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
        getSafeNavController()?.navigateUp()
    }
}