package com.example.productme.feature_protect.domain.use_case

data class ProtectMeUseCases(
    val addGuardUseCase: AddGuardUseCase,
    val deleteGuardUseCase: DeleteGuardUseCase,
    val getGuardUseCase: GetGuardUseCase,
    val getGuardsUseCase: GetGuardsUseCase
)
