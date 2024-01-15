package com.example.pfootball.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pfootball.databinding.MatchesItemChildBinding
import com.example.pfootball.models.matches.Response
import com.example.pfootball.myutils.Utilities

class MatchesChildAdapter(private val childList: List<Response>) :
    RecyclerView.Adapter<MatchesChildAdapter.MatchesChildViewHolder>() {

    class MatchesChildViewHolder(private val binding: MatchesItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun binItem(matchChild: Response) {
            binding.nameTeam1.text = matchChild.teams.home.name
            binding.nameTeam2.text = matchChild.teams.away.name
            if (matchChild.fixture.status.short == "FT") {
                binding.scoreTeam1.text = matchChild.goals.home.toString()
                binding.scoreTeam2.text = matchChild.goals.away.toString()
            }

            Glide.with(itemView.context).load(matchChild.teams.home.logo).into(binding.logoTeam1)
            Glide.with(itemView.context).load(matchChild.teams.away.logo).into(binding.logoTeam2)

            setDayTime(matchChild)

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setDayTime(matchChild: Response) {
            if (matchChild.fixture.status.short == "FT") {
                binding.dayStart.text = "FT"
                binding.timeStart.text =
                    Utilities.convertISO8601ToLocalDate(matchChild.fixture.date)
            } else {
                binding.dayStart.text = Utilities.convertISO8601ToLocalDate(matchChild.fixture.date)
                binding.timeStart.text =
                    Utilities.convertISO8601ToLocalTime(matchChild.fixture.date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesChildViewHolder {
        val binding =
            MatchesItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchesChildViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MatchesChildViewHolder, position: Int) {
        holder.binItem(childList[position])
    }


}