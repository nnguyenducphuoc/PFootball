package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pfootball.databinding.TeamHeaderItemBinding
import com.example.pfootball.databinding.TeamItemBinding
import com.example.pfootball.models.Standings


class TeamAdapter(private val teamList: List<Standings>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TeamViewHolder(binding)
        } else {
            val binding =
                TeamHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TeamHeaderViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return teamList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            (holder as TeamViewHolder).binTeamItem(teamList[position - 1])
        }
    }

    class TeamViewHolder(private val binding: TeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binTeamItem(team: Standings) {
            binding.stt.text = team.rank.toString()
            binding.team.text = team.team.name
            Glide.with(itemView.context).load(team.team.logo).into(binding.logoTeam)

            setTop(team)
        }

        private fun setTop(team: Standings) {
            when (team.rank) {
                1, 2, 3, 4 -> binding.view.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.pfootball.R.color.blue
                    )
                )

                5, 6 -> binding.view.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.pfootball.R.color.orange
                    )
                )

                7 -> binding.view.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.pfootball.R.color.green
                    )
                )

                18, 19, 20 -> binding.view.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        com.example.pfootball.R.color.red
                    )
                )
            }
        }
    }

    class TeamHeaderViewHolder(val binding: TeamHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}