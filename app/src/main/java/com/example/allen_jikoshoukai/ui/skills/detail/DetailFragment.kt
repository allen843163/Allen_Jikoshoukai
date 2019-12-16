package com.example.allen_jikoshoukai.ui.skills.detail

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Property
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.widget.ConstraintAttribute
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.allen_jikoshoukai.BR
import com.example.allen_jikoshoukai.R
import com.example.allen_jikoshoukai.adapter.MySimpleAdapter
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillCategoryBinding
import com.example.allen_jikoshoukai.databinding.ViewpagerSkillDetailBinding
import com.example.allen_jikoshoukai.remote.model.Category
import com.example.allen_jikoshoukai.remote.model.Detail
import com.example.allen_jikoshoukai.ui.architecture.BaseFragment
import com.example.allen_jikoshoukai.ui.skills.SkillsViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import kotlin.reflect.KProperty

class DetailFragment : BaseFragment() {
    private val skillsViewModel : SkillsViewModel by sharedViewModel()

    lateinit var binding : ViewpagerSkillDetailBinding

    var detailAdapter = object : MySimpleAdapter<Detail>(
        mutableListOf()
        , BR.detailData
        , R.layout.lv_item_skill_detail) {

        override fun onViewItemSetting(holder: BaseViewHolder, view: View, item: Detail, position: Int) {
            super.onViewItemSetting(holder, view, item, position)

            holder.setIsRecyclable(false)

            var layoutMotion = view.findViewById<MotionLayout>(R.id.layout_content)

            var endConstraint = layoutMotion.getConstraintSet(R.id.end)
                .getConstraint(R.id.pg_level)

            endConstraint.mCustomConstraints.put("Progress", ConstraintAttribute("Progress", ConstraintAttribute.AttributeType.INT_TYPE, item.Level))

            layoutMotion.transitionToEnd()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

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