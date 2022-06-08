package com.example.productme.feature_protect.domain.use_case

import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.domain.repository.ProtectMeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GetGuardsUseCase(
    private val repository: ProtectMeRepository
) {
    operator fun invoke(): Flow<List<Guard>> {
      return repository.getGuards()
   }
}
