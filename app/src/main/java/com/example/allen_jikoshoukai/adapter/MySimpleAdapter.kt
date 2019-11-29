package com.example.allen_jikoshoukai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

open class MySimpleAdapter<T>(
    private var itemDatas: MutableList<T>,
    private var brId: Int,
    private var layout: Int
) : RecyclerView.Adapter<MySimpleAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        Timber.d("onCreateViewHolder : ".plus(viewType))
        Timber.d("onCreateViewHolder : ".plus(parent.id))

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        Timber.d("onCreateViewHolder")

        return BaseViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return layout
    }

    override fun getItemCount(): Int {
        return itemDatas.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.binding.setVariable(brId, itemDatas[position])

        onViewItemSetting(holder.binding.root, itemDatas[position], position)

        holder.binding.executePendingBindings()
    }

    open fun onViewItemSetting(view: View, item: T, position: Int) {

    }

    fun getData() : MutableList<T> {
        return itemDatas
    }

    class BaseViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}