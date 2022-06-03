package com.example.productme.core.presentaion.screen.splash_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productme.core.comm.Constants
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : ViewModel() {

    private val _actionEventChannel = Channel<ActionEvent>()
    val actionEventChannel=_actionEventChannel.receiveAsFlow()

    private fun navigate(){
        viewModelScope.launch {
            delay(Constants.SPLASH_SCREEN_TIME_OUT)
            _actionEventChannel.send(ActionEvent.NavigateToHome)

        }
    }

    sealed class ActionEvent {
        object NavigateToHome : ActionEvent()
    }
}