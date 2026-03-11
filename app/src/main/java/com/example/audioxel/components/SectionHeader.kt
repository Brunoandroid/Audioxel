package com.example.audioxel.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.ui.theme.OnBackground
import com.example.audioxel.ui.theme.Primary

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = OnBackground,
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.labelLarge,
                color = Primary,
            )
        }
    }
}
