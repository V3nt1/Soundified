package com.example.soundified2.MainActivity.Fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soundified2.HelpingScripts.PlaylistAdapter
import com.example.soundified2.HelpingScripts.PlaylistData
import com.example.soundified2.R
import com.example.soundified2.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var _binding: FragmentFirstBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Llamamos a changecolor para setear el color de la letra y el background de la actionBar
        changeColor()

        //Llamamos a initRecyclerView para que nos cargue en pantalla la lista de playlists.
        initRecylerView()


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        initRecylerView()
    }
    fun initRecylerView(){
        val recyclerView = _binding.recyclerView

        //Le asignamos a adapter la vista que queremos que se cree para que se muestre nuestra
        //lista de playlists.
        val adapter = PlaylistAdapter(PlaylistData.list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
    }

    override fun onStart() {
        super.onStart()
        changeColor()
    }

    private fun changeColor() {
        (activity as? AppCompatActivity)?.supportActionBar?.title =
            Html.fromHtml("<font color=\"#FAC400\">Playlists</font>")
        val colorDrawable = ColorDrawable(Color.parseColor("#333333"))
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}