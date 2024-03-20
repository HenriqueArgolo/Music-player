package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henriqueargolo.musicappplayer.R
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.MineSongBinding

class MineSong : Fragment() {
    private lateinit var binding: MineSongBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var  aduifile: AudioFile

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MineSongBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object{
        fun newInstance(audioFile: AudioFile): MineSong{
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

        mediaPlayer = MediaPlayer()
        miniSongInfo()
        backToFullScreen()
    }

    fun miniSongInfo(){
        try {
            val audioFile = arguments?.getSerializable("audioFile") as AudioFile
            if(audioFile != null){
                binding.mineSong.visibility = View.VISIBLE
                binding.mineTitle.text = audioFile.title
        }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun backToFullScreen(){
        val screen = FullScreenPlayer()
        binding.mineSong.setOnClickListener {
            binding.mineSong.visibility = View.GONE
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_layout, screen)
                .commit()
        }
    }



    fun mineSongTime(mediaPlayer: MediaPlayer) {
        val seconds = mediaPlayer.currentPosition / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        val progressText = String.format("%02d:%02d", minutes, remainingSeconds)
        binding.miniTime.text = progressText
    }

}