package com.example.productme.feature_protect.presentaion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.curativepis.ui.theme.spacing
import com.example.productme.R
import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.ui.theme.green

@Composable
fun GuardCard(
    modifier: Modifier = Modifier,
    guard: Guard,
) {
    Card(
        contentColor = MaterialTheme.colors.surface,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
        shape = MaterialTheme.shapes.large,
    ) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = MaterialTheme.spacing.large, vertical = MaterialTheme.spacing.regulator)
                .clip(MaterialTheme.shapes.large),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = green,
                        modifier = Modifier.align(CenterVertically)
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }

            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.regulator)
            ) {
                Text(text = stringResource(R.string.guard_card_name),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary)
                Text(text = guard.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.regulator)
            ) {
                Text(text = stringResource(R.string.guard_card_phone),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary)
                Text(text = guard.phone,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface)
            }

        }
    }
}