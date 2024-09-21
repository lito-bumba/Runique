package com.bumba.runique

import android.app.Application
import com.bumba.auth.data.di.authDataModule
import com.bumba.auth.presentation.di.authViewModelModule
import com.bumba.core.data.di.coreDataModule
import com.bumba.run.presentation.di.runViewModelModule
import com.bumba.runique.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runViewModelModule
            )
        }
    }
}