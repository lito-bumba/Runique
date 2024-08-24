package com.bumba.core.domain.util

sealed interface Result<out D, out E : Error> {

    data class Success<out D>(val data: D) : Result<D, Nothing>

    data class Error<out E : com.bumba.core.domain.util.Error>(val error: E) : Result<Nothing, E>

}

enum class PasswordValidationError : Error {
    TOO_SHORT,
    MISSING_ONE_DIGIT
}

fun validatePassword(password: String): Result<Unit, PasswordValidationError> {
    return Result.Error(PasswordValidationError.MISSING_ONE_DIGIT)
}

fun handleError(result: Result<Unit, PasswordValidationError>) {
    when (result) {
        is Result.Success -> {

        }
        is Result.Error -> {
            when(result.error) {
                PasswordValidationError.TOO_SHORT -> TODO()
                PasswordValidationError.MISSING_ONE_DIGIT -> TODO()
            }
        }
    }
}