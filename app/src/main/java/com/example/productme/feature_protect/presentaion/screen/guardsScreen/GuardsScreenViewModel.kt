package com.example.productme.feature_protect.presentaion.screen.guardsScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productme.feature_protect.domain.use_case.ProtectMeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuardsScreenViewModel @Inject constructor(
    private val useCases: ProtectMeUseCases,
) : ViewModel() {
    var uiState by mutableStateOf(GuardScreenState())
    private val _validationEventChannel = Channel<UiEvent>()
    val validationEvent = _validationEventChannel.receiveAsFlow()

    init {
        getGuards()
    }
    fun onEvent(event: GuardsScreenEvent){
        when(event){
            is GuardsScreenEvent.DeleteGuard->{
                viewModelScope.launch {
                    useCases.deleteGuardUseCase(guard = event.guard)
                }
            }
            else -> {}
        }
    }

    private fun getGuards() {
      useCases.getGuardsUseCase()
          .onEach {guards->
              uiState=uiState.copy(
                  guards=guards
              )
          }.launchIn(viewModelScope)
    }
}
sealed class UiEvent{
    object Success:UiEvent()
    object Navigate:UiEvent()
    data class Error(val message:String):UiEvent()
}