package com.example.audioxel.screens.musics.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.components.SectionHeader
import com.example.audioxel.components.TrackItem
import com.example.audioxel.data.model.soundcloud.SoundCloudTrack

@Composable
fun TrackSection(
    tracks: List<SoundCloudTrack>,
    onTrackClick: (SoundCloudTrack) -> Unit,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(R.string.nav_library),
            onSeeAllClick = onSeeAllClick
        )

        tracks.forEach { track ->
            TrackItem(
                track = track,
                onClick = { onTrackClick(track) }
            )
        }
    }
}
