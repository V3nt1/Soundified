package com.example.soundified2.HelpingScripts

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.soundified2.PlaylistActivity.PlaylistActivity
import com.example.soundified2.R

class PlaylistAdapter(val list: List<Playlist>):RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {

    inner class PlaylistHolder(val view:View):RecyclerView.ViewHolder(view){
        fun render(playlist: Playlist){
            //Se le setea el texto de la playlist en la lista de playlists al de
            //la playlist enviada.
            view.findViewById<TextView>(R.id.tvNamePlaylist).text = playlist.name

            //Hacemos que la playlist pueda ser clickada, de forma que cuando le demos
            //inicie una nueva activity que tendrá unos datos extra enviados a traves de
            //los intent.putExtra(). Esto nos ayudará más adelante a setear ciertos campos
            //en la activity.
            view.findViewById<CardView>(R.id.cardView).setOnClickListener{

                val intent = Intent(view.context, PlaylistActivity::class.java)
                //// To pass any data to next activity
                intent.putExtra("name", playlist.name)
                intent.putExtra("description", playlist.description)
                intent.putExtra("index", playlist.index)
                //// start your next activity
                view.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlaylistHolder(layoutInflater.inflate(R.layout.item_playlist,parent,false))
    }

    override fun onBindViewHolder(holder: PlaylistHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}



