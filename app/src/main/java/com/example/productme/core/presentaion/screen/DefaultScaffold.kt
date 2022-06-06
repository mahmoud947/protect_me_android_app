package com.example.productme.core.presentaion.screen

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.core.comm.Constants
import com.example.productme.core.presentaion.navigation.MyNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScaffold(
    navController: NavHostController,
    context: Context
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


    Scaffold(
        scaffoldState = scaffoldState,
        drawerBackgroundColor = MaterialTheme.colors.primary,
        drawerElevation = MaterialTheme.spacing.medium,
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        drawerContentColor = MaterialTheme.colors.primary,
        drawerContent = {
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen

    ) {
        MyNavGraph(scaffoldState = scaffoldState, navController = navController,context=context)
    }

}
