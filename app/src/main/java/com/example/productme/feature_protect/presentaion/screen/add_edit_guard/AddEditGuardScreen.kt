package com.example.productme.feature_protect.presentaion.screen.add_edit_guard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.DefaultTextField
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.feature_protect.presentaion.components.GuardCard

@Composable
fun AddEditGuardScreen(
    navController:NavController,
    scaffoldState: ScaffoldState
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,

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
            Image(painter = painterResource(id = R.drawable.ic_undraw_add_files_re_v09g),
                contentDescription = null)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))
        var name by remember {
            mutableStateOf("")
        }
        var phone by remember {
            mutableStateOf("")
        }

        DefaultTextField(value = name, label ="Guard Name", TrailingIcon = Icons.Default.Person, onTextChange = { name=it })
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        DefaultTextField(value = phone, label ="Guard Phone", TrailingIcon = Icons.Default.Phone, onTextChange = {phone=it})


    }
}