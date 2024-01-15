package com.example.pfootball.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pfootball.fragment.MatchesFragment
import com.example.pfootball.fragment.NewsFragment
import com.example.pfootball.fragment.PlayersFragment
import com.example.pfootball.fragment.StandingsFragment
import com.example.pfootball.fragment.StatsFragment
import com.example.pfootball.mvvm.StandingViewModel


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val viewModel: StandingViewModel
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    lateinit var fragment: Fragment
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                fragment = MatchesFragment()
                fragment.arguments = createBundle()
                return fragment
            }

            1 -> {

                fragment = NewsFragment()
                fragment.arguments = createBundle()
                return fragment
            }

            2 -> {

                fragment = StandingsFragment()
                fragment.arguments = createBundle()
                return fragment
            }

            3 -> {

                fragment = StatsFragment()
                fragment.arguments = createBundle()
                return fragment
            }

            else -> {

                fragment = PlayersFragment()
                fragment.arguments = createBundle()
                return fragment
            }
        }
    }

    private fun createBundle(): Bundle {
        val bundle = Bundle()
        viewModel.selectedItemData?.let { bundle.putInt("id", it) }
        return bundle
    }


}