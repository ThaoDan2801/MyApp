package com.glucozo.music.fragment

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddona.music.adapter.SongAdapter
import com.ddona.music.service.MusicService
import com.ddona.music.util.Const
import com.glucozo.music.MainActivity
import com.glucozo.music.adapter.OnSongItemClickListener
import com.glucozo.music.databinding.FragmentSongBinding
import com.glucozo.music.media.MediaLoader
import com.glucozo.music.media.MediaManager

class SongFragment : Fragment(), OnSongItemClickListener {
    private lateinit var binding: FragmentSongBinding
    private lateinit var musicService: MusicService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongBinding.inflate(inflater, container, false)
        binding.lvSong.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            Log.d(
                "doanpt",
                "fragment Song size:${MediaLoader.getInstance(requireContext()).arrSong.size}"
            )
            adapter = SongAdapter(
                MediaLoader.getInstance(requireContext()).arrSong,
                this@SongFragment
            )
        }
        requireContext().bindService(
            Intent(requireContext(), MusicService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
        return binding.root
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            binder?.let {
                musicService = (binder as MusicService.MusicBinder).getMusicService()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }

    override fun onClick(index: Int) {
//        musicService.playPauseSong(index)
        val playIntent = Intent(Const.ACT_PLAY_PAUSE)
        requireContext().sendBroadcast(playIntent)
        (requireActivity() as MainActivity).updateBottomLayout(true, MediaManager.songs[index])
    }
}