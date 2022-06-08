package com.example.productme.feature_protect.presentaion.screen.add_edit_guard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.domain.use_case.ProtectMeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGuardViewModel @Inject constructor(
    private val useCases: ProtectMeUseCases,
) : ViewModel() {

    var uiState by mutableStateOf(AddEditGuardState())

    private val _validationEventChannel = Channel<UiEvent>()
    val validationEvent = _validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddEditScreenEvent) {
        when (event) {
            is AddEditScreenEvent.NameChanged -> {
                uiState = uiState.copy(
                    name = event.name
                )
            }
            is AddEditScreenEvent.PhoneChanged -> {
                uiState = uiState.copy(
                    phone = event.phone
                )
            }
            is AddEditScreenEvent.SaveOrAddGuard -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val validateGuardName = useCases.validateGuardName(name = uiState.name)
        val validateGuardPhone = useCases.validateGuardPhone(phone = uiState.phone)

        val hasError = listOf(
            validateGuardName,
            validateGuardPhone
        ).any {
            !it.isValid
        }


        if (hasError) {
            uiState = uiState.copy(
                nameErrorMessage = validateGuardName.errorMessage,
                phoneErrorMessage = validateGuardPhone.errorMessage,
            )
            return
        } else {
            viewModelScope.launch {
                uiState = try {
                    useCases.addGuardUseCase(guard = Guard(name = uiState.name, phone = uiState.phone))
                    _validationEventChannel.send(UiEvent.GuardSaved)
                    uiState.copy(
                        isButtonEnable = false
                    )
                }catch (e:Exception){
                    _validationEventChannel.send(UiEvent.ShowSnackBar(message = "This name already exists"))
                    uiState.copy(
                        isButtonEnable = true
                    )
                }

            }
        }

    }

    sealed class UiEvent {
        object GuardSaved : UiEvent()
        data class ShowSnackBar(val message:String):UiEvent()
    }

}