package com.henriqueargolo.musicappplayer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.henriqueargolo.musicappplayer.R

class FullScreenPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_player)
        val sheetPlaylist = findViewById<FrameLayout>(R.id.sheet_playlist)
        BottomSheetBehavior.from(sheetPlaylist).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}