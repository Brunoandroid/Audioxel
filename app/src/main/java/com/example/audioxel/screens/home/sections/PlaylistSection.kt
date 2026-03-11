package com.example.audioxel.screens.home.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.components.PlaylistCard
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.data.model.PlaylistItem
import com.example.audioxel.ui.theme.Dimens

@Composable
fun PlaylistSection(
    items: List<PlaylistItem>,
    modifier: Modifier = Modifier,
    onItemClick: (PlaylistItem) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(R.string.section_playlists),
            onSeeAllClick = onSeeAllClick,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
            contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
        ) {
            items(items, key = { it.id }) { item ->
                PlaylistCard(
                    item = item,
                    onClick = { onItemClick(item) },
                )
            }
        }
    }
}