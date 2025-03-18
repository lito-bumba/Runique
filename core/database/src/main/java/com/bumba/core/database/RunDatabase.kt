package com.bumba.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bumba.core.database.dao.AnalyticsDao
import com.bumba.core.database.dao.RunDao
import com.bumba.core.database.dao.RunPendingSyncDao
import com.bumba.core.database.entity.DeletedRunSyncEntity
import com.bumba.core.database.entity.RunEntity
import com.bumba.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
    abstract val analyticsDao: AnalyticsDao
}