package com.henriqueargolo.musicappplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.henriqueargolo.musicappplayer.databinding.ActivityMainBinding
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.AllSongs
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.Playlist

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playlist = Playlist()
        navigatingThroughFragments(playlist)


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

    fun navigatingThroughFragments(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment)
        transaction.commit()
    }
}