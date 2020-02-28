package com.rikkeisoft.rmvvmsample.ui.login

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface LoginNavigator {

    fun handleError(throwable: Throwable)

    fun login()

    fun openMainActivity()
}