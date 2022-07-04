package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.databinding.FragmentBlankBinding
import com.roadster.roam.basesetup.ui.main.base.BaseViewModelFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : BaseViewModelFragment<FragmentBlankBinding, BlankViewModel, BlankViewState, BlankNavigation>() {
    override val layout: Int
        get() = R.layout.fragment_blank

    override val viewModel: BlankViewModel by viewModel()

    override fun setupViews() {
        TODO("Not yet implemented")
    }

    override fun showErrorMsg(title: CharSequence, subtitle: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun showSuccessMsg(title: CharSequence, subtitle: CharSequence?) {
        TODO("Not yet implemented")
    }

    override fun setViewState(viewState: BlankViewState) {
        TODO("Not yet implemented")
    }

    override fun navigateTo(navigation: BlankNavigation) {
        TODO("Not yet implemented")
    }

}