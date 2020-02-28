package com.rikkeisoft.rmvvmsample.data.remote

import com.rikkeisoft.rmvvmsample.data.model.api.LoginRequest
import com.rikkeisoft.rmvvmsample.data.model.api.LoginResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
@Singleton
class AppApiHelper @Inject
constructor(private val mApiHeader: ApiHeader) : ApiHelper {
    override val apiHeader: ApiHeader
        get() = mApiHeader



    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
            .addHeaders(mApiHeader.publicApiHeader)
            .addBodyParameter(request)
            .build()
            .getObjectSingle<LoginResponse>(LoginResponse::class.java)
    }
}