package com.bumba.run.data.di

import com.bumba.core.domain.run.SyncRunScheduler
import com.bumba.run.data.CreateRunWorker
import com.bumba.run.data.DeleteRunWorker
import com.bumba.run.data.FetchRunsWorker
import com.bumba.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}