package com.example.audioxel.components.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.audioxel.ui.theme.Dimens
import com.example.audioxel.ui.theme.OnSurface
import com.example.audioxel.ui.theme.OnSurfaceVariant
import com.example.audioxel.ui.theme.Surface

@Composable
fun MiniPlayer(
    trackName: String,
    artistName: String,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Surface)
            .clickable { /* Abrir Player Full Screen no futuro */ }
            .padding(horizontal = Dimens.PaddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Placeholder para imagem da capa
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(Dimens.RadiusSmall))
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.width(Dimens.PaddingMedium))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackName,
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = artistName,
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(onClick = onPlayPauseClick) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = OnSurface
            )
        }

        IconButton(onClick = onNextClick) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = "Next",
                tint = OnSurface
            )
        }
    }
}
