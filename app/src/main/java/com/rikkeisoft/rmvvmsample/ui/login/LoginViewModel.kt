package com.rikkeisoft.rmvvmsample.ui.login

import android.text.TextUtils
import com.rikkeisoft.rmvvmsample.data.DataManager
import com.rikkeisoft.rmvvmsample.data.model.api.LoginRequest
import com.rikkeisoft.rmvvmsample.ui.base.BaseViewModel
import com.rikkeisoft.rmvvmsample.utils.CommonUtils
import com.rikkeisoft.rmvvmsample.utils.rx.SchedulerProvider
import timber.log.Timber

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
class LoginViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false
        }
        return !TextUtils.isEmpty(password)
    }

    fun login(email: String, password: String) {
        setIsLoading(true)
        compositeDisposable.add(dataManager
            .doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
            .doOnSuccess { response ->
                response.accessToken?.let { accessToken ->
                    response.userName?.let { userName ->
                        response.userEmail?.let { userEmail ->
                            response.googleProfilePicUrl?.let { googleProfilePicUrl ->
                                dataManager
                                    .updateUserInfo(
                                        accessToken,
                                        response.userId,
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                        userName,
                                        userEmail,
                                        googleProfilePicUrl
                                    )
                            }
                        }
                    }
                }
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                Timber.tag("LOGIN_RESPONSE").d(response.userName)
                setIsLoading(false)
                getNavigator()?.openMainActivity()
            }, { throwable ->
                setIsLoading(false)
                getNavigator()?.handleError(throwable)
            })
        )
    }

    fun onServerLoginClick() {
        getNavigator()?.login()
    }
}