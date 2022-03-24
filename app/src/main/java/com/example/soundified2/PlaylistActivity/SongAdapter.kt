package com.example.soundified2.PlaylistActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soundified2.HelpingScripts.Song
import com.example.soundified2.R




class SongAdapter(private val list: List<Song>, val activity: PlaylistActivity):RecyclerView.Adapter<SongAdapter.SongHolder>()  {

    inner class SongHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun render(song: Song){

            view.findViewById<TextView>(R.id.tvNameSong).text = song.name

            //Si le damos al boton de una cancion, hacemos que empiece a sonar
            view.findViewById<TextView>(R.id.tvNameSong).setOnClickListener{
               activity.startASong(song.uri)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SongHolder(layoutInflater.inflate(R.layout.item_song,parent,false))
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}