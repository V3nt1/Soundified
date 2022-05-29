package com.example.soundified2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior



import android.widget.FrameLayout
import com.example.soundified2.HelpingScripts.Song
import com.example.soundified2.databinding.BottomSheetEditSongBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback


class EditSongBottomSheet(): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       return inflater.inflate(R.layout.bottom_sheet_edit_song, container, false)
    }

    companion object {
        const val TAG = "EditSongBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

        //Chage state of bottom sheet
        view.findViewById<Button>(R.id.btnDone).setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }


    }




    override fun onStart() {
        super.onStart()

    }
}
