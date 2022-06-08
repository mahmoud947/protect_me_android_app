package com.example.productme.core.presentaion.screen.splash_screen.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productme.core.comm.Constants
import com.example.productme.core.domian.use_case.CoreUseCases
import com.example.productme.core.presentaion.screen.splash_screen.SplashScreenState
import com.example.productme.core.util.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val useCases: CoreUseCases,
) : ViewModel() {

    var uiState by mutableStateOf(SplashScreenState())

    private val _actionEventChannel = Channel<ActionEvent>()
    val actionEventChannel = _actionEventChannel.receiveAsFlow()

    init {
        checkActivation()
    }


    private fun checkActivation() {
        useCases.checkActivationUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isActive = result.data?.isActive,
                        isLoading = false
                    )
                    if (result.data?.isActive == true){
                    delay(Constants.SPLASH_SCREEN_TIME_OUT)
                        _actionEventChannel.send(ActionEvent.NavigateToHome)
                    }
                }
                is Resource.Error -> {
                    uiState = uiState.copy(
                        isActive = false,
                        errorMessage = result.message
                    )
                }
                is Resource.Loading -> {
                    uiState = uiState.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SplashScreenEvent){
        when(event){
            is SplashScreenEvent.TryAgn->{
                checkActivation()
            }
        }
    }



    sealed class ActionEvent {
        object NavigateToHome : ActionEvent()
    }
}
sealed class SplashScreenEvent{
    object TryAgn:SplashScreenEvent()
}