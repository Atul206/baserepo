package com.roadster.roam.basesetup.ui.main


import androidx.lifecycle.viewModelScope
import com.roadster.roam.basesetup.viewmodel.BaseStateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val exampleUseCase: ExampleUseCase) :
    BaseStateViewModel<ExampleViewState, ExampleNavigation>() {

    fun getDataCheck() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                var result = exampleUseCase.execute()
                if (result) {
                    postViewState(ExampleViewState.success)
                } else {
                    navigateTo(ExampleNavigation.nextScreen)
                }
            }
        }
    }
}