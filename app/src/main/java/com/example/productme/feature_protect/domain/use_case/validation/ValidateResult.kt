package com.example.productme.feature_protect.domain.use_case.validation

data class ValidateResult(
    val isValid: Boolean,
    val errorMessage: String? = null,
)
