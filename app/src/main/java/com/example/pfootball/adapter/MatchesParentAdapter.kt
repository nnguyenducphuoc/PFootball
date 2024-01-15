package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfootball.databinding.MatchesItemParentBinding
import com.example.pfootball.models.matches.Response
import com.example.pfootball.myutils.Utilities

class MatchesParentAdapter(private val parentMatches: List<Response>) :
    RecyclerView.Adapter<MatchesParentAdapter.MatchesParentViewHolder>() {

    class MatchesParentViewHolder(private val binding: MatchesItemParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(matchParent: Response, parentMatches: List<Response>) {
            binding.round.text =
                "Matchday " + Utilities.extractNumber(matchParent.league.round) + " of 38"

            binding.recyclerViewParent.setHasFixedSize(true)
            binding.recyclerViewParent.layoutManager = LinearLayoutManager(itemView.context)
            val adapter = MatchesChildAdapter(parentMatches)
            binding.recyclerViewParent.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesParentViewHolder {
        val binding =
            MatchesItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchesParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchesParentViewHolder, position: Int) {
        holder.binItem(parentMatches[position], parentMatches)
    }


    override fun getItemCount(): Int {
        return (parentMatches.size / 10)
    }


}