package com.example.audioxel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audioxel.data.model.FeaturedItem
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnBackground
import com.example.audioxel.ui.theme.Primary

@Composable
fun FeaturedCard(
    item: FeaturedItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .width(Dimens.SizeXXXLarge)
            .height(Dimens.SizeXXXLarge)
            .clickable { onClick() },
        shape = RoundedCornerShape(Dimens.RadiusMedium),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFF9500).copy(alpha = 0.3f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.PaddingDefault),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Primary)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = item.label.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        ),
                        color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = OnBackground,
                )

                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnBackground.copy(alpha = 0.7f),
                )
            }
        }
    }
}
