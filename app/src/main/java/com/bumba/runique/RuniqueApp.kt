package com.bumba.runique

import android.app.Application
import android.content.Context
import com.bumba.auth.data.di.authDataModule
import com.bumba.auth.presentation.di.authViewModelModule
import com.bumba.core.data.di.coreDataModule
import com.bumba.core.database.di.databaseModule
import com.bumba.run.data.di.runDataModule
import com.bumba.run.location.di.locationModule
import com.bumba.run.network.di.networkModule
import com.bumba.run.presentation.di.runPresentationModule
import com.bumba.runique.di.appModule
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}