package com.example.audioxel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.example.audioxel.data.model.home.RecentItem
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnBackground
import com.example.audioxel.ui.theme.OnSurfaceVariant
import com.example.audioxel.ui.theme.SurfaceVariant

@Composable
fun RecentCard(
    item: RecentItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .width(Dimens.SizeXXLarge)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.SizeXXLarge)
                .clip(RoundedCornerShape(Dimens.RadiusSmall))
                .background(SurfaceVariant),
        )
        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            color = OnBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.labelMedium,
            color = OnSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
