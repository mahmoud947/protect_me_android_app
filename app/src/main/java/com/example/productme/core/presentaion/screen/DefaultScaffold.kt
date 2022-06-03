package com.example.productme.core.presentaion.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.productme.core.presentaion.navigation.MyNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScaffold(
    navController: NavHostController
) {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primary
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    )
SideEffect {
    systemUiController.setStatusBarColor(
        color = color,
        darkIcons = false
    )
}

    Scaffold() {
        MyNavGraph(scaffoldState = scaffoldState, navController = navController)
    }

}
