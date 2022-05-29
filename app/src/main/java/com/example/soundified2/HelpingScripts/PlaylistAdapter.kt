package com.example.soundified2.HelpingScripts

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.soundified2.PlaylistActivity.PlaylistActivity
import com.example.soundified2.R
import com.example.soundified2.databinding.ItemPlaylistBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class PlaylistAdapter(private val list: List<Playlist>,val activity: Activity):RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>() {

    inner class PlaylistHolder(private val view:View):RecyclerView.ViewHolder(view){
        fun render(playlist: Playlist){
            //Se le setea el texto de la playlist en la lista de playlists al de
            //la playlist enviada.
            val binding = ItemPlaylistBinding.bind(view)
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

            binding.btnEdit.setOnClickListener {
                val btnsheet = activity.layoutInflater.inflate(R.layout.bottom_sheet_edit_song, null)
                val dialog = BottomSheetDialog(activity)
                dialog.setContentView(btnsheet)
                dialog.show()
                val editText = btnsheet.findViewById<EditText>(R.id.editText)
                editText.setText(playlist.name)

                btnsheet.findViewById<Button>(R.id.btnDone).setOnClickListener {
                    val editText = btnsheet.findViewById<EditText>(R.id.editText)
                    playlist.name = editText.text.toString()
                    binding.tvNamePlaylist.text = playlist.name
                    dialog.dismiss()
                    notifyDataSetChanged()
                }
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



