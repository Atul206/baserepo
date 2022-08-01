package com.roadster.roam.basesetup.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.roadster.roam.basesetup.navigation.Navigation
import com.roadster.roam.basesetup.navigation.ViewState
import com.roadster.roam.basesetup.ui.main.base.BaseFragment
import com.roadster.roam.basesetup.viewmodel.BaseStateViewModel


abstract class BaseViewModelFragment<ViewBinding : ViewDataBinding,VIEW_MODEL : BaseStateViewModel<VIEW_STATE, NAVIGATION>, VIEW_STATE : ViewState, NAVIGATION : Navigation> :
    BaseFragment() {

    protected lateinit var binding: ViewBinding

    abstract val viewModel: VIEW_MODEL

    private val viewStateObserver by lazy {
        { state: VIEW_STATE -> setViewState(state) }
    }

    private val navigationObserver by lazy {
        { navigation: NAVIGATION -> navigateTo(navigation) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStates()
        backPressHandling()
    }

    abstract fun setViewState(viewState: VIEW_STATE)

    abstract fun navigateTo(navigation: NAVIGATION)

    private fun observeStates() {
        viewModel.apply {
            observeViewState(viewLifecycleOwner, viewStateObserver)
            observeNavigation(viewLifecycleOwner, navigationObserver)
            observeCommonErrors(viewLifecycleOwner, commonNetworkErrorObserver)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun backPressHandling(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}