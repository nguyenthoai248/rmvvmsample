package com.rikkeisoft.rmvvmsample.data.remote

import com.rikkeisoft.rmvvmsample.data.model.api.LoginRequest
import com.rikkeisoft.rmvvmsample.data.model.api.LoginResponse
import io.reactivex.Single

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface ApiHelper {

    fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

    val apiHeader: ApiHeader
}