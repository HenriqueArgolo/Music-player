package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.graphics.Color
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.henriqueargolo.musicappplayer.R
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.ActivityFullScreenPlayerBinding
import com.henriqueargolo.musicappplayer.ui.viewmodels.AudioMananger
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

class FullScreenPlayer : Fragment() {
    private lateinit var binding: ActivityFullScreenPlayerBinding
    private lateinit var audioManager: AudioManager
    var mediaPlayer = MediaPlayer()

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

        binding = ActivityFullScreenPlayerBinding.bind(view)

        BottomSheetBehavior.from(binding.sheetPlaylist).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        manipulateSongBySeekBar()
        volume()


    }

    fun seekBarManipulation() {
        val mainLooper = Looper.getMainLooper()
        val handle = Handler(mainLooper)
        val runnable = object : Runnable {
            override fun run() {
                calctime()
                var currentPosition = mediaPlayer.currentPosition / 1000
                binding.seekBarSong.max = mediaPlayer.duration / 1000
                binding.seekBarSong.progress = currentPosition
                if (mediaPlayer.isPlaying) handle.postDelayed(this, 1000)

            }

        }
        handle.postDelayed(runnable, 1000)

    }


    fun manipulateSongBySeekBar() {
        binding.seekBarSong.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    val songTIme = progress *1000
                    mediaPlayer.seekTo(songTIme)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }

        })
    }


    fun volume() {
        binding.seekbarAudio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val valor = progress.toFloat() / 100
                mediaPlayer.setVolume(valor, valor)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }

        })
    }

    fun playAndPauseSong(song: AudioFile) {
        mediaPlayer.reset()

        try {
            mediaPlayer.setDataSource(song.path)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->

                binding.playlistTitle.text = song.title
                binding.playlistSongName.text = song.title
                val valor = binding.seekbarAudio.progress.toFloat() / 100
                mediaPlayer.setVolume(valor, valor)
                mediaPlayer.start()
                seekBarManipulation()
                binding.playPauseBtn.setImageResource(R.drawable.pause_ic)


                binding.playPauseBtn.setOnClickListener {
                    if (mp.isPlaying) {
                        seekBarManipulation()
                        mediaPlayer.pause()
                        binding.playPauseBtn.setImageResource(R.drawable.play_ic)
                    } else {
                        seekBarManipulation()
                        mediaPlayer.start()
                        binding.playPauseBtn.setImageResource(R.drawable.pause_ic)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onCompleteListner(song: AudioFile) {
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer?) {
                binding.playPauseBtn.setImageResource(R.drawable.play_ic)
                binding.playPauseBtn.setOnClickListener {
                    playAndPauseSong(song)
                }
            }

        })
    }

    fun calctime() {
        val seconds = mediaPlayer.currentPosition / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        val progressText = String.format("%02d:%02d", minutes, remainingSeconds)
        binding.currentTime.text = progressText

        val fullTime = mediaPlayer.duration / 1000
        val fullMinutes = fullTime / 60
        val fullSeconds = fullTime % 60

        val resultMinutes = fullMinutes - minutes
        val resultSEconds = fullSeconds - remainingSeconds
        val fullText = String.format("-%02d:%02d", resultMinutes, resultSEconds)
        binding.fullSongTime.text = fullText
    }

}

