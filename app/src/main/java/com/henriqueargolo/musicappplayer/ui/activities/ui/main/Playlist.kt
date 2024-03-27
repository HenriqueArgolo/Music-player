package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.databinding.ActivityPlaylistBinding
import com.henriqueargolo.musicappplayer.ui.viewmodels.Player

class Playlist : Fragment() {
    private val mediaPlayer = Player.getInstance()
    private lateinit var binding: ActivityPlaylistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}