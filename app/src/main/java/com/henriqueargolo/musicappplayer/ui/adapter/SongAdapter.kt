package com.henriqueargolo.musicappplayer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henriqueargolo.musicappplayer.data.model.AudioFile
import com.henriqueargolo.musicappplayer.databinding.SongModelBinding
import com.henriqueargolo.musicappplayer.ui.activities.ui.main.FullScreenPlayer

class SongAdapter(
    private val context: Context,
    private val allSongs: List<AudioFile>,
    private val onItemClick: OnItemClick
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    interface OnItemClick {
        fun onItemClick(song: AudioFile)
    }

    class ViewHolder(val binding: SongModelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: AudioFile) {
            binding.SongTitle.text = song.title
            binding.Artist.text = song.artist
            binding.duration.text = song.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SongModelBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = allSongs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = allSongs[position]
        holder.bind(song)
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(song)
        }
    }
}
