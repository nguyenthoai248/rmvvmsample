package com.rikkeisoft.rmvvmsample.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.rikkeisoft.rmvvmsample.data.DataManager
import com.rikkeisoft.rmvvmsample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
abstract class BaseViewModel<N>(val dataManager: DataManager,
                                val schedulerProvider: SchedulerProvider) : ViewModel() {

    val isLoading = ObservableBoolean()

    val compositeDisposable: CompositeDisposable

    private var mNavigator: WeakReference<N>? = null

    private val navigator: N? = null

    fun getNavigator(): N? {
        return mNavigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }
}