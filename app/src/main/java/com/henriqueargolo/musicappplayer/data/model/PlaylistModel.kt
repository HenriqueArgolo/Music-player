package com.henriqueargolo.musicappplayer.data.model

data class PlaylistModel(
    val id: Int,
    val name: String,
    val playingSong: String,
    val songs: List<AudioFile>
)

