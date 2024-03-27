package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henriqueargolo.musicappplayer.R
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.MainActivity
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.MineSongBinding
import com.henriqueargolo.musicappplayer.ui.viewmodels.Player
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MineSong : Fragment() {
    private lateinit var binding: MineSongBinding
    var mediaPlayer = Player.getInstance()
    private lateinit var aduifile: AudioFile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MineSongBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(audioFile: AudioFile): MineSong {
            val fragment = MineSong()
            val args = Bundle().apply {
                putSerializable("audioFile", audioFile)
            }
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miniSongInfo()
        btnPlayerPause()
        backToFullScreen()
        countTime()

    }

    fun miniSongInfo() {
        try {
            val audioFile = arguments?.getSerializable("audioFile") as AudioFile
            if (audioFile != null) {
                binding.mineSong.visibility = View.VISIBLE
                binding.mineTitle.text = audioFile.title
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun btnPlayerPause() {
        binding.miniBtn.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                binding.miniBtn.setImageResource(R.drawable.play_ic)
                mediaPlayer.pause()
            } else {
                binding.miniBtn.setImageResource(R.drawable.pause_ic)
                mediaPlayer.start()
            }
        }
    }


    fun backToFullScreen() {
        val screen = FullScreenPlayer()
        binding.mineSong.setOnClickListener {
            binding.mineSong.visibility = View.GONE
            parentFragmentManager.beginTransaction().replace(R.id.container_layout, screen)
                .commit()
        }
    }

    fun countTime(){
        val mainLooper = Looper.getMainLooper()
        val handle = Handler(mainLooper)
        val runnable = object : Runnable {
            override fun run() {
               calcTime()
                if (mediaPlayer.currentPosition < mediaPlayer.duration) handle.postDelayed(this, 1000)
            }
        }
        handle.postDelayed(runnable, 1000)
    }

    fun calcTime() {
        val time = mediaPlayer.currentPosition / 1000
        val minute = time / 60
        val seconds = time % 60

        val stringFormat = String.format("%02d:%02d", minute, seconds)
        binding.miniTime.text = stringFormat
    }
}
