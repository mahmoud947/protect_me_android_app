package com.example.productme.feature_protect.presentaion.screen.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.ButtonWithElevation
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.ui.theme.green
import com.example.productme.ui.theme.orange
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
) {
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

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))

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
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.my_guards_btn),
            startIcon = Icons.Default.Security
        )

    }
}
