package com.henriqueargolo.musicappplayer.data.model

import java.io.Serializable

data class AudioFile(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Int,
    val path: String
):Serializable
