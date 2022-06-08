package com.example.productme.feature_protect.presentaion.screen.add_edit_guard

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.core.presentaion.components.ButtonWithElevation
import com.example.productme.core.presentaion.components.DefaultTextField
import com.example.productme.core.presentaion.components.DefaultTopAppBar
import com.example.productme.feature_protect.domain.model.Guard

@Composable
fun AddEditGuardScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    guard: Guard?=null,
    viewModel: AddEditGuardViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is AddEditGuardViewModel.UiEvent.GuardSaved -> {
                    navController.popBackStack()
                }
                is AddEditGuardViewModel.UiEvent.ShowSnackBar -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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


        DefaultTextField(value = state.name,
            label = "Guard Name",
            TrailingIcon = Icons.Default.Person,
            onTextChange = {
                viewModel.onEvent(AddEditScreenEvent.NameChanged(name = it))
            }, isError = state.nameErrorMessage != null)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
        if (state.nameErrorMessage != null) {
            Text(text = state.nameErrorMessage,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        DefaultTextField(value = state.phone,
            label = "Guard Phone",
            TrailingIcon = Icons.Default.Phone,
            onTextChange = {
                viewModel.onEvent(AddEditScreenEvent.PhoneChanged(phone = it))
            }, isError = state.phoneErrorMessage != null, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
        if (state.phoneErrorMessage != null) {
            Text(text = state.phoneErrorMessage,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        ButtonWithElevation(
            modifier = Modifier
                .height(MaterialTheme.spacing.smallButtonH)
                .width(MaterialTheme.spacing.smallButtonX),
            onClick = {
                viewModel.onEvent(AddEditScreenEvent.SaveOrAddGuard)
            },
            text = stringResource(id = R.string.add_edit_guard_btn),
            startIcon = Icons.Default.PersonAdd,
            isEnable = state.isButtonEnable
        )


    }
}