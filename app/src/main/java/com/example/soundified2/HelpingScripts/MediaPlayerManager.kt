package com.example.soundified2.HelpingScripts

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

object MediaPlayerManager {

    var mediaPlayer: MediaPlayer? = null

    //private var isMediaPlayerPaused: Boolean = false
    fun playMediaPlayer(activity: Activity, uri: Uri?) {
        if (mediaPlayer != null) {
            destroyMediaPlayer()
        }
        mediaPlayer = MediaPlayer.create(activity, uri)
        mediaPlayer?.start();
        //isMediaPlayerPaused = false
    }

    fun pauseMediaPlayer() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            //isMediaPlayerPaused = true
        }
    }

    fun stopMediaPlayer() {
        mediaPlayer?.stop()
        // isMediaPlayerPaused = false
    }

    fun destroyMediaPlayer() {
        mediaPlayer?.release()
        //isMediaPlayerPaused = false
    }
}
