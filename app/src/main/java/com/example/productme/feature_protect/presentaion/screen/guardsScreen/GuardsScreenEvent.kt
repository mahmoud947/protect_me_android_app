package com.example.productme.feature_protect.presentaion.screen.guardsScreen

import com.example.productme.feature_protect.domain.model.Guard

sealed class GuardsScreenEvent(){
    data class DeleteGuard(val guard: Guard):GuardsScreenEvent()
    object EditGuard:GuardsScreenEvent()
}
