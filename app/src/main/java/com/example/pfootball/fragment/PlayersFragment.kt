package com.example.pfootball.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pfootball.adapter.PlayerAdapter
import com.example.pfootball.databinding.FragmentPlayersBinding
import com.example.pfootball.models.ResponseX
import com.example.pfootball.mvvm.PlayerViewModel
import kotlinx.coroutines.launch

class PlayersFragment : Fragment() {
    private lateinit var binding: FragmentPlayersBinding
    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var mPlayerList: MutableList<ResponseX>
    private lateinit var mPlayerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPlayerList = ArrayList()
        mPlayerAdapter = PlayerAdapter(mPlayerList)
        playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]
        playerViewModel.getPlayers(39, 2023, 40, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = mPlayerAdapter
        observerPlayerLivedata()
    }

    private fun observerPlayerLivedata() {
        lifecycleScope.launch {
            playerViewModel.observerPlayerLivedata().observe(
                viewLifecycleOwner
            ) {
                mPlayerList = it.response as MutableList<ResponseX>
                mPlayerAdapter = PlayerAdapter(mPlayerList)
                binding.recyclerView.adapter = mPlayerAdapter
            }
        }
    }
}