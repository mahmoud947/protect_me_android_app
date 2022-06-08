package com.example.productme.feature_protect.domain.use_case

import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.domain.repository.ProtectMeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGuardUseCase(
    private val repository: ProtectMeRepository
) {
   suspend operator fun invoke(guard: Guard){
       repository.insertGuard(guard = guard)
   }
}
