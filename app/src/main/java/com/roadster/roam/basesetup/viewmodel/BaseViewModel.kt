package com.roadster.roam.basesetup.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import com.roadster.roam.basesetup.extensions.event
import com.roadster.roam.basesetup.network.BaseError
import com.roadster.roam.basesetup.network.NetworkError
import com.roadster.roam.basesetup.network.Result
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    protected val IO by lazy { CoroutineDispatcherProvider.getUseCaseDispatcher() }
    protected val MAIN by lazy { CoroutineDispatcherProvider.getMainDispatcher() }
    protected val COMPUTITION by lazy { CoroutineDispatcherProvider.getComputiotionDispatcher() }

    private val commonNetworkError = event<NetworkError>()

    protected fun handleCommonErrors(
        error: BaseError,
        otherErrorsHandler: (cause: BaseError) -> Unit = {}
    ) {
        when (error) {
            is NetworkError.Unauthorized,
            is NetworkError.ServerInternalError,
            is NetworkError.ServerTemporaryUnavailable,
            is NetworkError.ServerMaintenance,
            is NetworkError.Connection,
            is NetworkError.ConnectionTimeout -> commonNetworkError.postValue(error as NetworkError)
            else -> otherErrorsHandler(error)
        }
    }

    fun observeCommonErrors(owner: LifecycleOwner, observer: (BaseError) -> Unit) {
        commonNetworkError.observe(owner, observer)
    }

    fun Job?.cancelIfActive() {
        if (this != null && isActive) cancel()
    }

    protected inline fun <T> handleResult(
        result: Result<T?>,
        onSuccess: (T?) -> Unit = {},
        crossinline onError: (BaseError) -> Unit = {}
    ) {
        when (result) {
            is Result.Success -> onSuccess(result.data)
            is Result.Error -> handleCommonErrors(result.error) {
                onError(result.error)
            }
        }
    }
}