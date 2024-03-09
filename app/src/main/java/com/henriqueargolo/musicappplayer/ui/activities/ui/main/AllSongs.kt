package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.ActivityAllSongsBinding
import com.henriqueargolo.musicappplayer.ui.adapter.AllsongsAdapter
import com.henriqueargolo.musicappplayer.ui.viewmodels.AudioMananger

class AllSongs(): Fragment() {
    private lateinit var binding: ActivityAllSongsBinding
    private lateinit var audioadapter: AllsongsAdapter

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

        audioadapter = AllsongsAdapter(requireContext(), audioFile)
        binding.recyclerVIew.apply {
            adapter = audioadapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }



}