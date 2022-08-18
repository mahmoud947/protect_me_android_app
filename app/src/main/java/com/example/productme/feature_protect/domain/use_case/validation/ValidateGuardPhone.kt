package com.example.productme.feature_protect.domain.use_case.validation

class ValidateGuardPhone {
    operator fun invoke(phone: String): ValidateResult {
        if (phone.isBlank()) {
            return ValidateResult(
                isValid = false,
                errorMessage = "phone can't be blank"
            )
        }
        if (phone.length !=11) {
            return ValidateResult(
                isValid = false,
                errorMessage = "phone number is incorrect"
            )
        }

        return ValidateResult(isValid = true)
    }
}