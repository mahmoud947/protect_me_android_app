package com.example.productme.core.presentaion.navigation

sealed class Screens(val route:String){
    object SplashScreen: Screens(route = "splash_screen")
    object HomeScreen:Screens(route = "home_screen")
}
