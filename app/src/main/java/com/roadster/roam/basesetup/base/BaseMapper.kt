package com.roadster.roam.basesetup.base

import com.roadster.roam.basesetup.network.BaseResponse

interface BaseMapper<T : BaseResponse, K: BaseResponse> {
    fun mapForUI(t:T, k:K): K
}