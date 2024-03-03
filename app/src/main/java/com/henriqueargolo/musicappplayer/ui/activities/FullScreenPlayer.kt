package com.henriqueargolo.musicappplayer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.henriqueargolo.musicappplayer.R

class FullScreenPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_player)
    }
}