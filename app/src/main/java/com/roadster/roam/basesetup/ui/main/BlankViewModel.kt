package com.roadster.roam.basesetup.ui.main

import com.roadster.roam.basesetup.viewmodel.BaseStateViewModel

class BlankViewModel : BaseStateViewModel<BlankViewState, BlankNavigation>() {

    fun onBackPressed(){
        postViewState(BlankViewState.backPressed)
    }
}