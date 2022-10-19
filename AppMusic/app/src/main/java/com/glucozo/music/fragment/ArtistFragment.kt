package com.glucozo.music.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glucozo.music.databinding.FragmentArtisrtBinding


class ArtistFragment : Fragment() {
    private lateinit var binding: FragmentArtisrtBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtisrtBinding.inflate(inflater, container, false)
        return binding.root
    }
}