package com.example.productme.feature_protect.presentaion.screen.guardsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.core.presentaion.navigation.Screens
import com.example.productme.feature_protect.presentaion.components.GuardCard

@Composable
fun GuardsScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: GuardsScreenViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTopAppBar(
                title = stringResource(id = R.string.guards_screen_app_bar_text),
                navigationIcon = Icons.Default.ArrowBack,
                onClick = {
                    navController.popBackStack()
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(start = MaterialTheme.spacing.large)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_undraw_control_panel_re_y3ar),
                    contentDescription = null)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.regulator),
                contentPadding = PaddingValues(all = MaterialTheme.spacing.regulator),
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                items(items = viewModel.uiState.guards) { guard ->
                    GuardCard(
                        guard = guard,
                        onDeleteClick = { viewModel.onEvent(GuardsScreenEvent.DeleteGuard(guard = guard)) }
                    )
                }
            }

        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = MaterialTheme.spacing.medium),
            onClick = { navController.navigate(Screens.AddEditGuardScreen.route) },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}