package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pfootball.databinding.PlayerItemBinding
import com.example.pfootball.models.ResponseX

class PlayerAdapter(private val mListResponse: List<ResponseX>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    class PlayerViewHolder(private val binding: PlayerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(response: ResponseX) {
            binding.namePlayer.text = response.player.name
            binding.tvTeam.text = response.statistics[0].team.name
            binding.tvPosition.text = response.statistics[0].games.position

            Glide.with(itemView.context).load(response.player.photo).into(binding.imagePlayer)
            Glide.with(itemView.context).load(response.statistics[0].team.logo)
                .into(binding.imgLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mListResponse.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.binItem(mListResponse[position])
    }


}