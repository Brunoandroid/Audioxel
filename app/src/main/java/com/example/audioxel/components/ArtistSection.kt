package com.example.audioxel.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.R
import com.example.audioxel.data.model.soundcloud.SoundCloudUser

@Composable
fun ArtistSection(
    artists: List<SoundCloudUser>,
    onArtistClick: (SoundCloudUser) -> Unit,
    modifier: Modifier = Modifier,
    onSeeAllClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = stringResource(R.string.search_placeholder),
            onSeeAllClick = onSeeAllClick
        )

        artists.forEach { user ->
            UserItem(
                user = user,
                onClick = { onArtistClick(user) }
            )
        }
    }
}
