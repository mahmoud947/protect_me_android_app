package com.example.productme.feature_protect.presentaion.screen.home_screen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productme.core.comm.Constants
import com.example.productme.core.domian.use_case.CoreUseCases
import com.example.productme.core.presentaion.screen.splash_screen.view_model.SplashScreenViewModel
import com.example.productme.core.util.network.Resource
import com.example.productme.feature_protect.domain.use_case.ProtectMeUseCases
import com.example.productme.feature_protect.presentaion.screen.add_edit_guard.AddEditGuardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: ProtectMeUseCases,
    private val coreUseCases: CoreUseCases
) : ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())

    private val _validationEventChannel = Channel<UiEvent>()
    val validationEvent = _validationEventChannel.receiveAsFlow()
    init {
        val isServiceEnable=useCases.getServiceStateUseCase()
        uiState=uiState.copy(
            isServiceEnable = isServiceEnable
        )
        checkValidation()
    }

    private fun checkValidation() {
       coreUseCases.checkActivationUseCase().onEach { result ->
           when (result) {
               is Resource.Success -> {
                   uiState = uiState.copy(
                       isActive = result.data?.isActive
                   )
               }
               is Resource.Error -> {
                   uiState = uiState.copy(
                       isActive = false,
                       activationMessage = result.message
                   )
               }
               is Resource.Loading -> {
                   uiState = uiState.copy(
                       isActive = false,
                       activationMessage = result.message
                   )
               }
           }
       }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ChangeServiceState -> {
                uiState=uiState.copy(
                    isServiceEnable = !uiState.isServiceEnable
                )
                useCases.saveServiceStateUseCase(uiState.isServiceEnable)
                if (useCases.getServiceStateUseCase()){
                    viewModelScope.launch {
                        _validationEventChannel.send(UiEvent.StartService)
                    }
                }else{
                    viewModelScope.launch {
                        _validationEventChannel.send(UiEvent.StopService)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object StartService : UiEvent()
        object StopService : UiEvent()

    }
}