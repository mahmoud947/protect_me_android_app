package com.example.productme.feature_protect.presentaion.screen.home_screen

data class HomeScreenState(
    val isServiceEnable: Boolean = false,
    val isActive:Boolean?=false,
    val activationMessage:String?=null,
    val isGuardFound:Boolean=false
)
