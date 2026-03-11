package com.example.audioxel.data.model

import androidx.annotation.DrawableRes

data class RecentItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int? = null,
)

data class PlaylistItem(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int? = null,
)

data class FeaturedItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val label: String,
    @DrawableRes val imageRes: Int? = null,
)

data class QuickChoiceItem(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int? = null,
)
