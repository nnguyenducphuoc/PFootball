package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pfootball.databinding.Top5ItemBinding
import com.example.pfootball.models.LeagueTop5

class LeagueTop5Adapter(private val itemList: List<LeagueTop5>) :
    RecyclerView.Adapter<LeagueTop5Adapter.Top5ViewHolder>() {

    var onClickItem: ((LeagueTop5) -> Unit)? = null

    class Top5ViewHolder(private val binding: Top5ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(item: LeagueTop5) {
            binding.title.text = item.title
            binding.image.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top5ViewHolder {
        val binding = Top5ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Top5ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Top5ViewHolder, position: Int) {
        holder.binItem(itemList[position])

        holder.itemView.setOnClickListener { onClickItem?.invoke(itemList[position]) }
    }

}