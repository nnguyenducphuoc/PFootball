package com.example.pfootball.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pfootball.adapter.StatsParentAdapter
import com.example.pfootball.databinding.FragmentStatsBinding
import com.example.pfootball.models.Player
import com.example.pfootball.models.parentItemStats
import com.example.pfootball.mvvm.StatsViewModel
import kotlinx.coroutines.launch

class StatsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding
    private lateinit var mParentList: MutableList<parentItemStats>
    private lateinit var mtitle: String
    private lateinit var mPlayer: Player
    private lateinit var mStatsAdapter: StatsParentAdapter
    private lateinit var mStatsViewModel: StatsViewModel
    private var receivedData: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receivedData = arguments?.getInt("id")
        mParentList = ArrayList()
        mStatsAdapter = StatsParentAdapter(mParentList)
        mStatsViewModel = ViewModelProvider(this)[StatsViewModel::class.java]
        mStatsViewModel.getTopScores(receivedData, 2023)
        mStatsViewModel.getTopAssists(receivedData, 2023)
        mStatsViewModel.getTopYellowCards(receivedData, 2023)
        mStatsViewModel.getTopRedCard(receivedData, 2023)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = mStatsAdapter

        observerTopScore()
        observerTopAssists()
        observerTopYellowCard()
        observerTopRedCard()
    }

    private fun observerTopRedCard() {
        lifecycleScope.launch {
            mStatsViewModel.observerTopRedCardLivedata().observe(viewLifecycleOwner) {
                mtitle = "Red cards"
                mPlayer = it
                val parentItemStats = parentItemStats(mtitle, mPlayer)
                mParentList.add(parentItemStats)
                mStatsAdapter = StatsParentAdapter(mParentList)
                binding.recyclerView.adapter = mStatsAdapter
            }
        }
    }

    private fun observerTopYellowCard() {
        lifecycleScope.launch {
            mStatsViewModel.observerTopYellowCardStatsLivedata().observe(viewLifecycleOwner) {
                mtitle = "Yellow cards"
                mPlayer = it
                val parentItemStats = parentItemStats(mtitle, mPlayer)
                mParentList.add(parentItemStats)
                mStatsAdapter = StatsParentAdapter(mParentList)
                binding.recyclerView.adapter = mStatsAdapter
            }
        }
    }

    private fun observerTopAssists() {
        lifecycleScope.launch {
            mStatsViewModel.observerTopAssistStatsLivedata().observe(viewLifecycleOwner) {
                mtitle = "Assists"
                mPlayer = it
                val parentItemStats = parentItemStats(mtitle, mPlayer)
                mParentList.add(parentItemStats)
                mStatsAdapter = StatsParentAdapter(mParentList)
                binding.recyclerView.adapter = mStatsAdapter
            }
        }
    }

    private fun observerTopScore() {
        lifecycleScope.launch {
            mStatsViewModel.observerTopScoreStatsLivedata().observe(viewLifecycleOwner) {
                mtitle = "Goals"
                mPlayer = it
                val parentItemStats = parentItemStats(mtitle, mPlayer)
                mParentList.add(parentItemStats)
                mStatsAdapter = StatsParentAdapter(mParentList)
                binding.recyclerView.adapter = mStatsAdapter
            }
        }
    }
}