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
import com.henriqueargolo.musicappplayer.ui.adapter.SongAdapter
import com.henriqueargolo.musicappplayer.ui.viewmodels.AudioMananger

class AllSongs(): Fragment(), SongAdapter.OnItemClick {
    private lateinit var binding: ActivityAllSongsBinding
    private lateinit var audioadapter: SongAdapter
    private val player : FullScreenPlayer = FullScreenPlayer()


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
        val audioFile = audioMananger.getAllAudioFiles()

        audioadapter = SongAdapter(requireContext(), audioFile, this)
        binding.recyclerVIew.apply {
            adapter = audioadapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    override fun onItemClick(song: AudioFile) {
        navigation(player)
        player.playAndPauseSong(song)
        player.seekBarManipulation()

    }


    fun navigation(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment)
        transaction.commit()


    }


}