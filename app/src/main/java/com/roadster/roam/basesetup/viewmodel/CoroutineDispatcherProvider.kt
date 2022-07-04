package com.roadster.roam.basesetup.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object CoroutineDispatcherProvider {
    fun getUseCaseDispatcher(): CoroutineDispatcher = Dispatchers.IO
    fun getMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    fun getComputiotionDispatcher(): CoroutineDispatcher = Dispatchers.Default
}