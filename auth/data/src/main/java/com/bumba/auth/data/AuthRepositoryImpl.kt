package com.bumba.auth.data

import com.bumba.auth.domain.AuthRepository
import com.bumba.core.data.networking.post
import com.bumba.core.domain.util.DataError
import com.bumba.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}