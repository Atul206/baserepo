package com.neev.owner.learning

import com.neev.owner.network.ErrorEntity
import com.roadster.roam.basesetup.network.BaseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// type we can produce it but we cannot consume like modification
// params is basically we can consume it but we cannot produce.
/**
 * * [Type] is covariant which is defined as the type of response required by caller function
 * [Params] is contravariant  which defines the type of parameters which is passed by the function as input
 */
abstract class BaseUseCaseFlow<out Type, in Params> {

    protected var viewModelScope: CoroutineScope?=null

    abstract suspend fun run(params: Params): Flow<Either<ErrorEntity, Type>>


    operator fun invoke(
        viewModeScope: CoroutineScope,
        params: Params,
        onResult: (Either<ErrorEntity, Type>) -> Unit
    )  {
        this.viewModelScope = viewModelScope
        viewModeScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                run(params).collectLatest {
                    withContext(Dispatchers.Main){
                        onResult(it)
                    }
                }
            }
        }
    }

    class None

}