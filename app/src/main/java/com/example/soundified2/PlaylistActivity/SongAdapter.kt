package com.example.soundified2.PlaylistActivity

import android.content.Context.INPUT_METHOD_SERVICE
import android.opengl.GLES30
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.soundified2.EditSongBottomSheet
import com.example.soundified2.HelpingScripts.Song
import com.example.soundified2.R
import com.example.soundified2.databinding.ItemSongBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class SongAdapter(private val list: List<Song>, val activity: PlaylistActivity) :
    RecyclerView.Adapter<SongAdapter.SongHolder>() {

    inner class SongHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        var isEditMode = false;
        var textBefore = ""
        fun render(song: Song) {

            val binding = ItemSongBinding.bind(view)

            binding.editButton.setOnClickListener {

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SongHolder(layoutInflater.inflate(R.layout.item_song, parent, false))
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.render(list[position])

        val modalBottomSheet = EditSongBottomSheet()
        modalBottomSheet.show(activity.supportFragmentManager, EditSongBottomSheet.TAG)
        modalBottomSheet.showsDialog
        val modalBottomSheetBehavior = (modalBottomSheet.dialog as BottomSheetDialog).behavior
        modalBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

    }

    override fun getItemCount(): Int {
        return list.size
    }
}