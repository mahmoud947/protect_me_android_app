package com.example.productme.feature_protect.domain.use_case

import com.example.productme.feature_protect.domain.use_case.validation.ValidateGuardName
import com.example.productme.feature_protect.domain.use_case.validation.ValidateGuardPhone

data class ProtectMeUseCases(
    val addGuardUseCase: AddGuardUseCase,
    val deleteGuardUseCase: DeleteGuardUseCase,
    val getGuardUseCase: GetGuardUseCase,
    val getGuardsUseCase: GetGuardsUseCase,
    val validateGuardName: ValidateGuardName,
    val validateGuardPhone: ValidateGuardPhone,
    val saveServiceStateUseCase: SaveServiceStateUseCase,
    val getServiceStateUseCase: GetServiceStateUseCase
)
