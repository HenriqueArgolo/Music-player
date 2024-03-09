package com.henriqueargolo.musicappplayer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henriqueargolo.musicappplayer.data.model.PlaylistModel
import com.henriqueargolo.musicappplayer.databinding.PlaylistItemsBinding

class PlaylistItemAdapter(val context : Context,  val playlists: List<PlaylistModel>):
    RecyclerView.Adapter<PlaylistItemAdapter.ViewHolder> (){


    class ViewHolder(val postItemsBinding: PlaylistItemsBinding):RecyclerView.ViewHolder(postItemsBinding.root){
        fun bind(playlist: PlaylistModel){
            postItemsBinding.imagePlaylist
            postItemsBinding.playlistTitle.text = playlist.name
            postItemsBinding.playingNow.text = playlist.playingSong
        }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PlaylistItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        )


    override fun getItemCount(): Int =playlists.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val post = playlists[position]
        holder.bind(post)
    }


}