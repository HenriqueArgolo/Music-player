package com.henriqueargolo.musicappplayer.ui.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.henriqueargolo.musicappplayer.R
import com.henriqueargolo.musicappplayer.databinding.ActivityFullScreenPlayerBinding
import com.henriqueargolo.musicappplayer.databinding.ActivityMainBinding

class FullScreenPlayer : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var binding: ActivityFullScreenPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_player)
        binding = ActivityFullScreenPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this@FullScreenPlayer, R.raw.skyfall)
        val duration = mediaPlayer.duration
        binding.seekBarSong.max = duration

        playAndPauseSong()

        BottomSheetBehavior.from(binding.sheetPlaylist).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }


    }

    public fun playAndPauseSong() {
        binding.playPauseBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.playPauseBtn.setImageResource(R.drawable.play_ic)
            } else {
                mediaPlayer.start()
                seekBarManipulation()
                binding.playPauseBtn.setImageResource(R.drawable.pause_ic)

            }
        }
    }

    private fun seekBarManipulation() {
        val mainLooper = Looper.getMainLooper()
        val handle = Handler(mainLooper)

        val runnable = object : Runnable {
            override fun run() {
                val currentPositio = mediaPlayer.currentPosition
                binding.seekBarSong.progress = currentPositio
                calcTime(currentPositio)
                if (mediaPlayer.isPlaying) handle.postDelayed(this, 1000)

            }

        }
        handle.postDelayed(runnable, 1000)
    }

    private fun calcTime(time: Int) {
        val seconds = time / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        val progressText = String.format("%02d:%02d", minutes, remainingSeconds)
        binding.currentTime.text = progressText

        val fullTime = mediaPlayer.duration / 1000
        val fullMinutes = fullTime / 60
        val fullSeconds = fullTime % 60

        val resultMinutes = fullMinutes - minutes
        val resultSEconds = fullSeconds - remainingSeconds
        val fullText = String.format("-%02d:%02d",resultMinutes, resultSEconds)
        binding.fullSongTime.text = fullText


    }

}