package com.example.productme.core.presentaion

sealed class Screens(val route:String){
    object SplashScreen:Screens(route = "splash_screen")
}
