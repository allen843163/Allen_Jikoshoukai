package com.example.allen_jikoshoukai.ui.architecture

import androidx.fragment.app.Fragment
import com.example.allen_jikoshoukai.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseFragment : Fragment()
{
    private val mainViewModel : MainViewModel by sharedViewModel()

    protected fun getMainVM() : MainViewModel {
        return mainViewModel
    }
}