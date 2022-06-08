package com.example.productme.feature_protect.domain.use_case.validation

class ValidateGuardName {
    operator fun invoke(name: String): ValidateResult {
        if (name.isBlank()) {
            return ValidateResult(
                isValid = false,
                errorMessage = "name can't be blank"
            )
        }
        if (name.length < 7) {
            return ValidateResult(
                isValid = false,
                errorMessage = "name must be at least 8 characters"
            )
        }
        return ValidateResult(isValid = true)
    }
}