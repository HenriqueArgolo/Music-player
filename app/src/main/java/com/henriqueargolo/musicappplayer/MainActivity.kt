package com.henriqueargolo.musicappplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.databinding.ActivityMainBinding
import com.henriqueargolo.musicappplayer.databinding.MineSongBinding
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.AllSongs
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.FullScreenPlayer
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.Playlist

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val player: FullScreenPlayer = FullScreenPlayer()
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var bindingMini: MineSongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingMini = MineSongBinding.inflate(layoutInflater)

        mediaPlayer = MediaPlayer()


        window.statusBarColor = ContextCompat.getColor(this, R.color.black300)

        val playlist = Playlist()
        navigatingThroughFragments(playlist)

        val player = FullScreenPlayer()



    binding.bottomNavigation.setOnItemSelectedListener{item ->
        when(item.itemId) {
            R.id.playlist -> {
                 navigatingThroughFragments(playlist)
                true

            }
            R.id.all_songs -> {
                val allSongs = AllSongs()
                navigatingThroughFragments(allSongs)
                true
            }
            R.id.search -> {
                false
            }

            else -> {false}
        }
    }

    }
    fun getMediaPlayerInstance(): MediaPlayer{
        return mediaPlayer
    }


    fun navigatingThroughFragments(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment)
        transaction.commit()
    }

}