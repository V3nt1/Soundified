package com.example.soundified2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior



import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback


class EditSongBottomSheet(): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_edit_song, container, false)

    companion object {
        const val TAG = "EditSongBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet = dialog!!.findViewById<FrameLayout>(R.id.bsEditSong)
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        behavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // This is the crucial bit.
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dialog!!.cancel()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        }
        )
    }
}
