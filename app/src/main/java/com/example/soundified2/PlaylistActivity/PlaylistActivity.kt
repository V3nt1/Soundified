package com.example.soundified2.PlaylistActivity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.result.contract.ActivityResultContracts
import androidx.documentfile.provider.DocumentFile
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soundified2.HelpingScripts.Playlist
import com.example.soundified2.HelpingScripts.PlaylistData
import com.example.soundified2.HelpingScripts.Song
import com.example.soundified2.databinding.ActivityPlaylistBinding

class PlaylistActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPlaylistBinding

    private lateinit var namePlaylist: String
    private lateinit var description: String
    private var mediaPlayer: MediaPlayer? = null
    private var audio: AudioManager? = null
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recogemos los datos recibidos por intent.putExtra(), y se los
        //seteamos al titulo de la actionBar y al texto de la activity para que
        //de esta forma se muestren el nombre de la playlist y su descripcion.
        namePlaylist = intent.getStringExtra("name").toString()
        description = intent.getStringExtra("description").toString()
        index = intent.getIntExtra("index",0)

        this.supportActionBar?.title = Html.fromHtml("<font color=\"#FAC400\">$namePlaylist</font>",Html.FROM_HTML_MODE_LEGACY)

        val colorDrawable = ColorDrawable(Color.parseColor("#333333"))
        this.supportActionBar?.setBackgroundDrawable(colorDrawable)
        binding.tvDescription.text = description

        if(index >= 0) {
            initRecylerView()
        }

        audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audio!!.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0)

        //Le añade a la action bar el boton de la flecha arriba a la izquierda para poder volver
        //a la activity anterior
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.addSongButton.setOnClickListener {
            openDocumentLauncher.launch(arrayOf("audio/*"))
        }

    }

    private fun initRecylerView(){
        val recyclerView = binding.songView

        val adapter = SongAdapter(PlaylistData.list[index].songList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    //Coger la música del móvil del usuario
    private val openDocumentLauncher =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            if (uri == null) return@registerForActivityResult
            val file = DocumentFile.fromSingleUri(applicationContext, uri)

            //Hacemos una lista de canciones temporal
            val tmpSongList: MutableList<Song> = PlaylistData.list[index].songList
            val song = Song(file?.name, uri)
            tmpSongList.add(song)

            //Actualizamos nuestra playlist actual con la nueva lista de canciones
            val playlist = Playlist(namePlaylist, description, index, tmpSongList)
            PlaylistData.list[index] = playlist


            initRecylerView()
        }

    fun startASong(uri: Uri?)
    {
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.stop()
            mediaPlayer = MediaPlayer.create(this, uri)
            mediaPlayer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}