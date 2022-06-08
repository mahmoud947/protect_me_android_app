package com.example.productme.core.presentaion.screen.splash_screen

import com.example.productme.core.util.network.Resource

data class SplashScreenState(
    val errorMessage:String?=null,
    val isActive:Boolean?=true,
    val isLoading: Boolean=false
)
