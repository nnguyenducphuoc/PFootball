package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pfootball.databinding.StatsParentBinding
import com.example.pfootball.models.parentItemStats

class StatsParentAdapter(private val parentItemStats: List<parentItemStats>) :
    RecyclerView.Adapter<StatsParentAdapter.StatsParentViewHolder>() {
    class StatsParentViewHolder(val binding: StatsParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(parentItemStats: parentItemStats) {
            binding.figures.text = parentItemStats.title

            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    itemView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            val adapter = StatsChildAdapter(parentItemStats.player)
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsParentViewHolder {
        val binding = StatsParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsParentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parentItemStats.size
    }

    override fun onBindViewHolder(holder: StatsParentViewHolder, position: Int) {
        holder.binItem(parentItemStats[position])
    }
}