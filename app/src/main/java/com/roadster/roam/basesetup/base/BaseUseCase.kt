package com.roadster.roam.basesetup.base

import com.neev.owner.network.ErrorEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Type, in Params> {

    /**
     * this should be called from Coroutine Context and implemented by all Use cases
     * */
    abstract suspend fun run(param: Params): Either<ErrorEntity, Type>

    /**
     * This is an operator function which is invoked from the viewModel ,
     * it collects data from repo and pass the concrete data to viewModel
     */


    operator fun invoke(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Either<ErrorEntity, Type>) -> Unit
    ) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    val result = run(params)
                    withContext(Dispatchers.Main)
                    {
                        onResult(result)
                    }

                }
        }
    }




    /**
     * When our Network Call don't need any parameter pass this class
     */
    class None
}
