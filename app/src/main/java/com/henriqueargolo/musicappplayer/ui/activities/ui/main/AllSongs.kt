package com.henriqueargolo.musicappplayer.ui.activities.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.R
import com.henriqueargolo.musicappplayer.databinding.ActivityAllSongsBinding
import com.henriqueargolo.musicappplayer.databinding.ActivityPlaylistBinding

class AllSongs : Fragment() {
    private lateinit var binding: ActivityAllSongsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = ActivityAllSongsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}