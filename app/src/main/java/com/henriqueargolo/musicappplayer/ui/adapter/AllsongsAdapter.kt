package com.henriqueargolo.musicappplayer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.SongModelBinding

class AllsongsAdapter(val context: Context, var allSongs: List<AudioFile>): RecyclerView.Adapter<AllsongsAdapter.ViewHolder>() {


    class ViewHolder(val binding: SongModelBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(song: AudioFile){
            binding.SongTitle.text = song.title
            binding.Artist.text = song.artist
            binding.duration.text = song.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        SongModelBinding.inflate(LayoutInflater.from(context), parent, false)
    )


    override fun getItemCount(): Int = allSongs.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = allSongs[position]
        holder.bind(song)
    }
}