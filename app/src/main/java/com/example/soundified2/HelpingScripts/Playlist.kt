package com.example.soundified2.HelpingScripts

data class Playlist(
    val name: String,
    val description: String,
    val index: Int = -1,

    var songList: MutableList<Song> = mutableListOf()
)
