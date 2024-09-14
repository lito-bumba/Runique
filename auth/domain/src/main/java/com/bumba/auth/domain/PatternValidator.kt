package com.bumba.auth.domain

interface PatternValidator {

    fun matches(value: String): Boolean
}