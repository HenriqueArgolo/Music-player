package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henriqueargolo.musicappplayer.R
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.MainActivity
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.MineSongBinding
import com.henriqueargolo.musicappplayer.ui.viewmodels.Player

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
    }
