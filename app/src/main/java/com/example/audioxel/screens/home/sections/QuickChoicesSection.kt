package com.example.audioxel.screens.home.sections

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.components.QuickChoiceCard
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.data.model.home.QuickChoiceItem
import com.example.audioxel.ui.theme.Dimens

@Composable
fun QuickChoicesSection(
    items: List<QuickChoiceItem>,
    modifier: Modifier = Modifier,
    onItemClick: (QuickChoiceItem) -> Unit = {},
    onSeeAllClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(R.string.section_escolhas_rapidas),
            onSeeAllClick = onSeeAllClick,
        )

        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.PaddingSmall),
                horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)
            ) {
                rowItems.forEach { item ->
                    QuickChoiceCard(
                        item = item,
                        modifier = Modifier.weight(1f),
                        onClick = { onItemClick(item) }
                    )
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}