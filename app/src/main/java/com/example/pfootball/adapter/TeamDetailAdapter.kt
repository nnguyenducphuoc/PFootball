package com.example.pfootball.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pfootball.R
import com.example.pfootball.databinding.DetailTeamHeaderItemBinding
import com.example.pfootball.databinding.DetailTeamItemBinding
import com.example.pfootball.models.Standings


class TeamDetailAdapter(private val teamList: List<Standings>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                DetailTeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TeamDetailViewHolder(binding)
        } else {
            val binding =
                DetailTeamHeaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return TeamDetailHeaderViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return teamList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            (holder as TeamDetailViewHolder).binTeamItem(teamList[position - 1])
        }
    }

    class TeamDetailViewHolder(private val binding: DetailTeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var charList: CharArray
        fun binTeamItem(team: Standings) {
            binding.mp.text = team.all.played.toString()
            binding.win.text = team.all.win.toString()
            binding.draw.text = team.all.draw.toString()
            binding.lost.text = team.all.lose.toString()
            binding.pts.text = team.points.toString()
            binding.gf.text = team.all.goals.`for`.toString()
            binding.ga.text = team.all.goals.against.toString()
            binding.gd.text = team.goalsDiff.toString()

            charList = team.form.toCharArray()
            setLast5()

        }

        private fun setLast5() {
            when (charList[0]) {
                'L' -> binding.last1.setImageResource(R.drawable.lost_img)
                'D' -> binding.last1.setImageResource(R.drawable.draw_img)
                'W' -> binding.last1.setImageResource(R.drawable.win_img)
            }
            when (charList[1]) {
                'L' -> binding.last2.setImageResource(R.drawable.lost_img)
                'D' -> binding.last2.setImageResource(R.drawable.draw_img)
                'W' -> binding.last2.setImageResource(R.drawable.win_img)
            }
            when (charList[2]) {
                'L' -> binding.last3.setImageResource(R.drawable.lost_img)
                'D' -> binding.last3.setImageResource(R.drawable.draw_img)
                'W' -> binding.last3.setImageResource(R.drawable.win_img)
            }
            when (charList[3]) {
                'L' -> binding.last4.setImageResource(R.drawable.lost_img)
                'D' -> binding.last4.setImageResource(R.drawable.draw_img)
                'W' -> binding.last4.setImageResource(R.drawable.win_img)
            }

            try {
                when (charList[4]) {
                    'L' -> binding.last5.setImageResource(R.drawable.lost_img)
                    'D' -> binding.last5.setImageResource(R.drawable.draw_img)
                    'W' -> binding.last5.setImageResource(R.drawable.win_img)
                }
            } catch (_: Exception) {
                binding.last5.setImageResource(R.drawable.draw_img)
            }
        }
    }

    class TeamDetailHeaderViewHolder(private val binding: DetailTeamHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}