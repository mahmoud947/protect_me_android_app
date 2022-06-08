package com.example.productme.core.presentaion.navigation

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.productme.core.presentaion.screen.splash_screen.SplashScreen
import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.presentaion.screen.add_edit_guard.AddEditGuardScreen
import com.example.productme.feature_protect.presentaion.screen.guardsScreen.GuardsScreen
import com.example.productme.feature_protect.presentaion.screen.home_screen.HomeScreen

@Composable
fun MyNavGraph(scaffoldState:ScaffoldState,navController: NavHostController,context: Context) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ){

        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screens.HomeScreen.route){
            HomeScreen(
                navController = navController,
                scaffoldState = scaffoldState,
            )
        }
        composable(route = Screens.GuardsScreen.route){
            GuardsScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(route = Screens.AddEditGuardScreen.route){
            AddEditGuardScreen(
                navController = navController,
                scaffoldState = scaffoldState,
            )
        }
    }

}