package com.bumba.auth.domain

import com.bumba.core.domain.util.DataError
import com.bumba.core.domain.util.EmptyResult

interface AuthRepository {

    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>

    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}