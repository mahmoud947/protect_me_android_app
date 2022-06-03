package com.example.productme.core.presentaion.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.productme.core.presentaion.Screens
import com.example.productme.core.presentaion.screen.splash_screen.SplashScreen

@Composable
fun MyNavGraph(scaffoldState:ScaffoldState,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ){

        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)
        }
    }

}