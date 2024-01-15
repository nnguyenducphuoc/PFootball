package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pfootball.databinding.StatsHeaderBinding
import com.example.pfootball.databinding.StatsItemBinding
import com.example.pfootball.models.Player
import com.example.pfootball.models.ResponseX

class StatsChildAdapter(private val player: Player) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_ITEM = 1
    private val VIEW_HEADER = 2

    class StatsChildViewHolder(val binding: StatsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(responseX: ResponseX, position: Int, player: Player) {
            binding.tvStt.text = (position + 1).toString()
            binding.tvNameTeam.text = responseX.statistics[0].team.name
            binding.nameStats.text = responseX.player.name

            Glide.with(itemView.context).load(responseX.player.photo).into(binding.statsImgPlayer)
            Glide.with(itemView.context).load(responseX.statistics[0].team.logo)
                .into(binding.statsImg)

            when (player.get) {
                "players/topscorers" -> binding.goals.text =
                    responseX.statistics[0].goals.total.toString()

                "players/topassists" -> binding.goals.text =
                    responseX.statistics[0].goals.assists.toString()

                "players/topyellowcards" -> binding.goals.text =
                    responseX.statistics[0].cards.yellow.toString()

                "players/topredcards" -> binding.goals.text =
                    responseX.statistics[0].cards.red.toString()
            }
        }
    }

    class StatsChildHeaderViewHolder(val binding: StatsHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binItem(player: Player) {
            when (player.get) {
                "players/topscorers" ->
                    binding.stats.text = "Goals"

                "players/topassists" ->
                    binding.stats.text = "Assists"

                "players/topyellowcards" ->
                    binding.stats.text = "Yellow Card"

                "players/topredcards" ->
                    binding.stats.text = "Red Card"


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_ITEM) {
            val binding =
                StatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StatsChildViewHolder(binding)
        } else {
            val binding =
                StatsHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StatsChildHeaderViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_ITEM) {
            if (position < 6) {
                (holder as StatsChildViewHolder).binItem(
                    player.response[position - 1],
                    position - 1,
                    player
                )
            }

        } else {
            (holder as StatsChildHeaderViewHolder).binItem(player)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_HEADER else VIEW_ITEM
    }
}