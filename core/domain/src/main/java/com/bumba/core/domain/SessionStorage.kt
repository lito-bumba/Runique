package com.bumba.core.domain

interface SessionStorage {

    suspend fun get(): AuthInfo?

    suspend fun set(info: AuthInfo?)
}