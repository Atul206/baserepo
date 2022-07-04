package com.roadster.roam.basesetup

import com.roadster.roam.basesetup.databinding.ActivityMainBinding
import com.roadster.roam.basesetup.ui.main.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override val graphId: Int
        get() = R.navigation.example_navigation
    override val navHostFragmentId: Int
        get() = R.id.navigation_host_fragment
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun showInfoMsg(message: CharSequence?) {
        TODO("Not yet implemented")
    }


}