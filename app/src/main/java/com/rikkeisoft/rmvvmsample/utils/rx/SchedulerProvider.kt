package com.rikkeisoft.rmvvmsample.utils.rx

import io.reactivex.Scheduler

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}