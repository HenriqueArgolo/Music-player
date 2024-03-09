package com.henriqueargolo.musicappplayer.data.model

data class AudioFile(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Int,
    val path: String
)
