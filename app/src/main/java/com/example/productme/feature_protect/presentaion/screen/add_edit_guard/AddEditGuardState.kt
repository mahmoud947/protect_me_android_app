package com.example.productme.feature_protect.presentaion.screen.add_edit_guard

data class AddEditGuardState(
    val name:String="",
    val nameErrorMessage:String? = null,
    val phone:String="",
    val phoneErrorMessage:String? = null,
    val isButtonEnable:Boolean=true
)
