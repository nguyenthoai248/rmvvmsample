package com.rikkeisoft.rmvvmsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rikkeisoft.rmvvmsample.data.DataManager
import com.rikkeisoft.rmvvmsample.ui.login.LoginViewModel
import com.rikkeisoft.rmvvmsample.utils.rx.SchedulerProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
@Singleton
class ViewModelProviderFactory @Inject
constructor(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        /* if (modelClass.isAssignableFrom(AboutViewModel::class.java!!)) {

             return AboutViewModel(dataManager, schedulerProvider) as T
         } else if (modelClass.isAssignableFrom(FeedViewModel::class.java!!)) {

             return FeedViewModel(dataManager, schedulerProvider) as T
         } else */if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

            return LoginViewModel(dataManager, schedulerProvider) as T
        } /*else if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {

            return MainViewModel(dataManager, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(BlogViewModel::class.java!!)) {

            return BlogViewModel(dataManager, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(RateUsViewModel::class.java!!)) {

            return RateUsViewModel(dataManager, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(OpenSourceViewModel::class.java!!)) {

            return OpenSourceViewModel(dataManager, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(SplashViewModel::class.java!!)) {

            return SplashViewModel(dataManager, schedulerProvider) as T
        }*/
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}