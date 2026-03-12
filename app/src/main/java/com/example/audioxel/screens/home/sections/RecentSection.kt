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
import com.example.audioxel.components.RecentCard
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.ui.theme.Dimens

@Composable
fun RecentSection(
    items: List<RecentItem>,
    modifier: Modifier = Modifier,
    onItemClick: (RecentItem) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(R.string.section_recents),
            onSeeAllClick = onSeeAllClick,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium),
            contentPadding = PaddingValues(vertical = Dimens.PaddingSmall),
        ) {
            items(items, key = { it.id }) { item ->
                RecentCard(
                    item = item,
                    onClick = { onItemClick(item) },
                )
            }
        }
    }
}