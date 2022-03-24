package com.example.soundified2.MainActivity.Fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.soundified2.HelpingScripts.Playlist
import com.example.soundified2.HelpingScripts.PlaylistData
import com.example.soundified2.R
import com.example.soundified2.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var currentIndex: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Seteamos el color de la letra y el fondo de la actionBar
        (activity as? AppCompatActivity)?.supportActionBar?.title = Html.fromHtml("<font color=\"#FAC400\">Añadir nueva playlist</font>")
        val colorDrawable = ColorDrawable(Color.parseColor("#333333"))
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)

        //Cuando se le da click al botón de crear playlist, se genera una playlist con lo
        //que haya introducido el usuario en los campos de texto y a continuación se
        //añade a playlist data para que se muestre en el primer fragment.
        binding.buttonSecond.setOnClickListener {

            val textName = binding.itNamePlaylist.text
            val desc = binding.tvDescription.text
            currentIndex = PlaylistData.list.count()

            when {
                textName.isNullOrEmpty() -> {
                    Toast.makeText(activity, "The name of the playlist cannot be blank!", Toast.LENGTH_SHORT).show()
                }
                desc.isNullOrEmpty() -> {
                    Toast.makeText(activity, "The description of the playlist cannot be blank!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val playList = Playlist(textName.toString(), desc.toString(), currentIndex)
                    PlaylistData.list.add(playList)
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        //Setamos el color de la letra y el fondo de la actionBar
        (activity as? AppCompatActivity)?.supportActionBar?.title = Html.fromHtml("<font color=\"#FAC400\">Añadir nueva playlist</font>")
        val colorDrawable = ColorDrawable(Color.parseColor("#333333"))
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}