package com.example.productme.feature_protect.presentaion.screen.home_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.ButtonWithElevation
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.core.presentaion.navigation.Screens
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm
import com.example.productme.service.ptotect_me.utils.sendCommandToService
import com.example.productme.ui.theme.darkBlue
import com.example.productme.ui.theme.green
import com.example.productme.ui.theme.orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    context: Context
) {
    val contextt= LocalContext.current
    val isDarkMode = isSystemInDarkTheme()
    val isServiceRun = remember {
        mutableStateOf(false)
    }
    val stateText = remember {
        mutableStateOf("ON")
    }
    if (isServiceRun.value) {
        stateText.value = "ON"
    } else {
        stateText.value = "OFF"
    }
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primary
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
    }


    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTopAppBar(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
            title = stringResource(id = R.string.home_screen_app_bar_text))

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
        ) {
            val homeImage: Int =
                if (isDarkMode) R.drawable.ic_undraw_safe_re_kiil_dark else R.drawable.ic_undraw_safe_re_kiil
            Image(painter = painterResource(id = homeImage),
                contentDescription = null)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(text = "State: ",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground)
            Text(text = stateText.value,
                style = MaterialTheme.typography.h5,
                color = if (isServiceRun.value) green else orange)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Switch(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .align(Alignment.Start),
            checked = isServiceRun.value, onCheckedChange = {
                isServiceRun.value = !isServiceRun.value
                if (isServiceRun.value){
                   contextt.sendCommandToService(ProtectMeServiceComm.ACTION_START_OR_RESUME_SERVICE)
                }

            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = green,
                uncheckedThumbColor = orange
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))

        ButtonWithElevation(
            modifier = Modifier
                .height(MaterialTheme.spacing.smallButtonH)
                .width(MaterialTheme.spacing.smallButtonX),
            onClick = {
                      //navController.navigate(route = Screens.GuardsScreen.route)
                contextt.sendCommandToService(ProtectMeServiceComm.ACTION_START_OR_RESUME_SERVICE)
            },
            text = stringResource(id = R.string.my_guards_btn),
            startIcon = Icons.Default.Security
        )

    }
}
