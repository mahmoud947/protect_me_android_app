package com.example.productme.feature_protect.presentaion.screen.add_edit_guard

sealed class AddEditScreenEvent{
    data class NameChanged(val name:String):AddEditScreenEvent()
    data class PhoneChanged(val phone:String):AddEditScreenEvent()
    object SaveOrAddGuard:AddEditScreenEvent()
}
