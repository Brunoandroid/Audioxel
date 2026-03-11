package com.example.audioxel.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnBackground

@Composable
fun TopGreetingBar(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.greeting_text),
            style = MaterialTheme.typography.titleLarge,
            color = OnBackground,
        )
        IconButton(onClick = onNotificationClick) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = stringResource(R.string.notifications),
                tint = OnBackground,
                modifier = Modifier.size(Dimens.SizeSmall),
            )
        }
    }
}
