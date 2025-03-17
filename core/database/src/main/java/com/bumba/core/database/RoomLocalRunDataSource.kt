package com.bumba.core.database

import android.database.sqlite.SQLiteFullException
import com.bumba.core.database.dao.RunDao
import com.bumba.core.database.mappers.toRun
import com.bumba.core.database.mappers.toRunEntity
import com.bumba.core.domain.run.LocalRunDataSource
import com.bumba.core.domain.run.Run
import com.bumba.core.domain.run.RunId
import com.bumba.core.domain.util.DataError
import com.bumba.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalRunDataSource(
    private val runDao: RunDao
) : LocalRunDataSource {

    override fun getRuns(): Flow<List<Run>> {
        return runDao.getRuns()
            .map { runEntities ->
                runEntities.map { runEntity ->
                    runEntity.toRun()
                }
            }
    }

    override suspend fun upsertRun(run: Run): Result<RunId, DataError.Local> {
        return try {
            val entity = run.toRunEntity()
            runDao.upsertRun(run.toRunEntity())
            Result.Success(entity.id)
        } catch (e: SQLiteFullException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertRuns(runs: List<Run>): Result<List<RunId>, DataError.Local> {
        return try {
            val entities = runs.map { run -> run.toRunEntity() }
            runDao.upsertRuns(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteRun(id: String) {
        runDao.deleteRun(id)
    }

    override suspend fun deleteAllRuns() {
        runDao.deleteAllRuns()
    }

}