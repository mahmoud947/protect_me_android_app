package com.example.productme.feature_protect.presentaion.screen.home_screen

import android.widget.Toast
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.ButtonWithElevation
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.core.presentaion.navigation.Screens
import com.example.productme.feature_protect.presentaion.screen.add_edit_guard.AddEditGuardViewModel
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm
import com.example.productme.service.protect_me.utils.sendCommandToService
import com.example.productme.ui.theme.green
import com.example.productme.ui.theme.orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState
    val context = LocalContext.current
    val isDarkMode = isSystemInDarkTheme()
    val stateText = remember {
        mutableStateOf("ON")
    }
    if (state.isServiceEnable) {
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



    LaunchedEffect(key1 = true) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is HomeScreenViewModel.UiEvent.StartService -> {
                    context.sendCommandToService(ProtectMeServiceComm.ACTION_START_OR_RESUME_SERVICE)
                }
                is HomeScreenViewModel.UiEvent.StopService -> {
                    context.sendCommandToService(ProtectMeServiceComm.ACTION_STOP_SERVICE)
                }
            }
        }
    }



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
                color = if (state.isServiceEnable) green else orange)
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Switch(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .align(Alignment.Start),
            checked = state.isServiceEnable, onCheckedChange = {
                viewModel.onEvent(HomeScreenEvent.ChangeServiceState)
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
                navController.navigate(route = Screens.GuardsScreen.route)

            },
            text = stringResource(id = R.string.my_guards_btn),
            startIcon = Icons.Default.Security
        )

    }


}
