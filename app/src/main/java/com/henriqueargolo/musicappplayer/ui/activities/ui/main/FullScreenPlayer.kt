package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.henriqueargolo.musicappplayer.R
import com.henriqueargolo.musicappplayer.databinding.ActivityFullScreenPlayerBinding

class FullScreenPlayer : Fragment() {
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var binding: ActivityFullScreenPlayerBinding
    private lateinit var audioManager: AudioManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = ActivityFullScreenPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.skyfall)
        val duration = mediaPlayer.duration
        binding.seekBarSong.max = duration

        playAndPauseSong()
        BottomSheetBehavior.from(binding.sheetPlaylist).apply {
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

        fun seekBarManipulation() {
            val mainLooper = Looper.getMainLooper()
            val handle = Handler(mainLooper)

            val runnable = object : Runnable {
                override fun run() {
                    val currentPositio = mediaPlayer.currentPosition
                    binding.seekBarSong.progress = currentPositio
                    calctime(currentPositio)
                    if (mediaPlayer.isPlaying) handle.postDelayed(this, 1000)

                }

            }
            handle.postDelayed(runnable, 1000)
        }

        fun playAndPauseSong() {
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


    private fun calctime(time: Int) {
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
