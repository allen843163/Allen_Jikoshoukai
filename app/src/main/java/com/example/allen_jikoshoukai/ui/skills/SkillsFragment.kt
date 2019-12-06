package com.example.allen_jikoshoukai.ui.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.allen_jikoshoukai.BR

import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.FragmentSkillsBinding
import com.example.allen_jikoshoukai.remote.model.Skill
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class SkillsFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by viewModel()

    lateinit var binding : FragmentSkillsBinding

    var skillAdapter = object : MySimpleAdapter<Skill>(
        mutableListOf()
        , BR.skillData
        ,R.layout.lv_item_skills
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

        binding = FragmentSkillsBinding.inflate(inflater)

        binding.mainVM = getMainVM()

        return binding.root
    }

    override fun onOptionItemBackClicked(item: MenuItem?) {
       getSafeNavController()
           ?.navigate(R.id.nav_self_introduction)
    }
}