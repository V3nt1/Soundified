package com.example.soundified2.PlaylistActivity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.soundified2.HelpingScripts.Song
import com.example.soundified2.R
import com.example.soundified2.databinding.ActivityMainBinding.inflate
import com.example.soundified2.databinding.ActivityPlaylistBinding.inflate
import com.example.soundified2.databinding.BottomSheetEditSongBinding
import com.example.soundified2.databinding.ItemSongBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class SongAdapter(private val list: List<Song>, val activity: PlaylistActivity) :
    RecyclerView.Adapter<SongAdapter.SongHolder>() {
    inner class SongHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun render(song: Song) {
            val binding = ItemSongBinding.bind(view)
            binding.tvNameSong.text = song.name

            binding.editButton.setOnClickListener {
                val btnsheet = activity.layoutInflater.inflate(R.layout.bottom_sheet_edit_song, null)
                val dialog = BottomSheetDialog(activity)
                dialog.setContentView(btnsheet)
                dialog.show()
                val editText = btnsheet.findViewById<EditText>(R.id.editText)
                editText.setText(song.name)

                btnsheet.findViewById<Button>(R.id.btnDone).setOnClickListener {
                    val editText = btnsheet.findViewById<EditText>(R.id.editText)
                    song.name = editText.text.toString()
                    binding.tvNameSong.text = song.name
                    dialog.dismiss()
                    notifyDataSetChanged()
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SongHolder(layoutInflater.inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {

        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}