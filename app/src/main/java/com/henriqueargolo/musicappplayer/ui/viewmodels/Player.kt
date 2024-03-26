package com.henriqueargolo.musicappplayer.ui.viewmodels

import android.media.MediaPlayer


object Player{

    private var mediaPlayer: MediaPlayer? = null

    fun getInstance(): MediaPlayer {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }
        return mediaPlayer!!
    }
}

