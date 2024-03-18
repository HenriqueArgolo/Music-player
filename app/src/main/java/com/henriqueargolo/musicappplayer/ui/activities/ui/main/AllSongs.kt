package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriqueargolo.musicappplayer.R
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.ActivityAllSongsBinding
import com.henriqueargolo.musicappplayer.databinding.ActivityFullScreenPlayerBinding
import com.henriqueargolo.musicappplayer.ui.adapter.SongAdapter
import com.henriqueargolo.musicappplayer.ui.viewmodels.AudioMananger
import java.util.zip.Inflater

class AllSongs() : Fragment(), SongAdapter.OnItemClick {
    private lateinit var binding: ActivityAllSongsBinding
    private lateinit var bindingPlay: ActivityFullScreenPlayerBinding
    private lateinit var audioadapter: SongAdapter
    private val player: FullScreenPlayer = FullScreenPlayer()
    private lateinit var list: List<AudioFile>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = ActivityAllSongsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val audioMananger = AudioMananger(requireContext())
        list = audioMananger.getAllAudioFiles()

        audioadapter = SongAdapter(requireContext(), list, this)
        binding.recyclerVIew.apply {
            adapter = audioadapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onItemClick(position: Int) {
        bindingPlay = ActivityFullScreenPlayerBinding.inflate(layoutInflater)
        player.mediaPlayer.stop()
        val song = list[position]
        if (player.mediaPlayer.isPlaying) {
            player.mediaPlayer.stop()
            player.mediaPlayer.reset()
        }

        navigation(player)
        player.playAndPauseSong(song)
        player.seekBarManipulation()
        player.onCompletionListner()


        if (bindingPlay.nextSong.isAttachedToWindow){
            bindingPlay.nextSong.setOnClickListener {
                val nextSong = list[position + 1]
                player.playAndPauseSong(nextSong)
                player.seekBarManipulation()
            }
        }

    }




    fun navigation(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment)
        transaction.commit()

    }


}