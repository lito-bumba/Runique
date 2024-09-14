package com.bumba.auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasNumber = password.any { it.isDigit() }
        val hasLowerCaseCharacther = password.any { it.isLowerCase() }
        val hasUpperCaseCharacther = password.any { it.isUpperCase() }
        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasNumber,
            hasLowerCaseCharacther = hasLowerCaseCharacther,
            hasUpperCaseCharacther = hasUpperCaseCharacther
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}