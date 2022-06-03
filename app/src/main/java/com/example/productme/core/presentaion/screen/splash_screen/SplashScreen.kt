package com.example.productme.core.presentaion.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.navigation.Screens
import com.example.productme.core.presentaion.screen.splash_screen.view_model.SplashScreenViewModel
import com.example.productme.ui.theme.darkBlue
import com.example.productme.ui.theme.orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collect

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val color = darkBlue
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
    }

    LaunchedEffect(key1 = true){
    viewModel.actionEventChannel.collect{event->
        when(event){
            is SplashScreenViewModel.ActionEvent.NavigateToHome->{
                navController.navigate(route = Screens.HomeScreen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        inclusive=true
                    }
                }
            }
        }
    }
}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(darkBlue),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(2f))
        Box(modifier = Modifier
            .height(200.dp)
            .padding(top = MaterialTheme.spacing.large)
            .weight(7f, fill = true)) {
            Image(painter = painterResource(id = R.drawable.splash_screen_logo),
                contentDescription = null)
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = stringResource(id = R.string.splash_screen_fotter),
            style = MaterialTheme.typography.h4,
            color = orange,
            textAlign = TextAlign.Center
        )

    }

}