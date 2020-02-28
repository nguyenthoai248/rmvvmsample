package com.rikkeisoft.rmvvmsample.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rikkeisoft.rmvvmsample.BuildConfig
import com.rikkeisoft.rmvvmsample.R
import com.rikkeisoft.rmvvmsample.data.AppDataManager
import com.rikkeisoft.rmvvmsample.data.DataManager
import com.rikkeisoft.rmvvmsample.data.local.db.DbHelper
import com.rikkeisoft.rmvvmsample.data.local.prefs.PreferencesHelper
import com.rikkeisoft.rmvvmsample.data.remote.ApiHeader
import com.rikkeisoft.rmvvmsample.data.remote.ApiHelper
import com.rikkeisoft.rmvvmsample.data.remote.AppApiHelper
import com.rikkeisoft.rmvvmsample.di.ApiInfo
import com.rikkeisoft.rmvvmsample.utils.AppConstants
import com.rikkeisoft.rmvvmsample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

/**
 * Created by ThoaiNguyen on 2/28/2020.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @ApiInfo
    internal fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java!!, dbName).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideProtectedApiHeader(@ApiInfo apiKey: String,
                                           preferencesHelper: PreferencesHelper): ApiHeader.ProtectedApiHeader {
        return ApiHeader.ProtectedApiHeader(
            apiKey,
            preferencesHelper.currentUserId,
            preferencesHelper.accessToken)
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}