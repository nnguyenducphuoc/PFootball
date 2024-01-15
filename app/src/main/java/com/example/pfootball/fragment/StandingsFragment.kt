package com.example.pfootball.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pfootball.R
import com.example.pfootball.adapter.TeamAdapter
import com.example.pfootball.adapter.TeamDetailAdapter
import com.example.pfootball.databinding.FragmentStandingsBinding
import com.example.pfootball.models.Standings
import com.example.pfootball.models.season.SeasonX
import com.example.pfootball.mvvm.StandingViewModel


class StandingsFragment : Fragment() {
    private lateinit var standingViewModel: StandingViewModel
    private lateinit var mTeamList: List<Standings>
    private lateinit var mSeasonList: List<SeasonX>
    private lateinit var mTeamAdapter: TeamAdapter
    private lateinit var mSeasonAdapter: ArrayAdapter<SeasonX>
    private lateinit var mTeamDetailAdapter: TeamDetailAdapter
    private lateinit var binding: FragmentStandingsBinding
    private lateinit var dividerItemDecoration: DividerItemDecoration
    private var receivedData: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receivedData = arguments?.getInt("id")
        standingViewModel = ViewModelProvider(this)[StandingViewModel::class.java]
        standingViewModel.getStanding(receivedData, 2023, null)
        standingViewModel.getSeason(receivedData)
        mTeamList = ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setSpinnerSelected()
        observerStanding()
        observerSeason()
        initRecyclerView()
        loadingStanding()
    }

    private fun loadingStanding() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressbar.visibility = View.GONE
            binding.nestedScrollView.visibility = View.VISIBLE
        }, 1800)
    }

    private fun observerSeason() {
        standingViewModel.observerSeasonLiveData().observe(viewLifecycleOwner) {
            mSeasonList = it.response[0].seasons
            mSeasonList = mSeasonList.asReversed()

            initSpinner()
        }
    }

    private fun initSpinner() {
        // Đổ dữ liệu vào Spinner
        mSeasonAdapter = object : ArrayAdapter<SeasonX>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mSeasonList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val season = getItem(position)
                val view = super.getView(position, convertView, parent)
                if (season != null) {
                    val textView = view.findViewById<TextView>(android.R.id.text1)
                    textView.text = "${season.year}" + "-" + "${season.year + 1 - 2000}"
                }
                return view
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val season = getItem(position)
                val view = super.getDropDownView(position, convertView, parent)
                if (season != null) {
                    val textView = view.findViewById<TextView>(android.R.id.text1)
                    textView.text = "${season.year}" + "-" + "${season.year + 1 - 2000}"
                }
                return view
            }
        }

        mSeasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.seasonSpinner.adapter = mSeasonAdapter
    }


    private fun observerStanding() {
        standingViewModel.observerStandingsLiveData().observe(viewLifecycleOwner) {
            mTeamList = it.response[0].league.standings[0]

            mTeamAdapter = TeamAdapter(mTeamList)
            mTeamDetailAdapter = TeamDetailAdapter(mTeamList)
            binding.recyclerView1.adapter = mTeamAdapter
            binding.recyclerView2.adapter = mTeamDetailAdapter
        }
    }

    private fun initDivider() {
        dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider
            )!!
        )
    }

    private fun initRecyclerView() {
        initDivider()

        binding.recyclerView1.layoutManager = LinearLayoutManager(context)
        binding.recyclerView1.addItemDecoration(dividerItemDecoration)

        binding.recyclerView2.layoutManager = LinearLayoutManager(context)
        binding.recyclerView2.addItemDecoration(dividerItemDecoration)


    }

    private fun setSpinnerSelected() {
        binding.seasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // Xử lý khi một mục được chọn
                val selectedItem = mSeasonList[position]
                standingViewModel.getStanding(
                    receivedData,
                    selectedItem.year,
                    null
                )
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Được gọi khi không có mục nào được chọn
            }
        }
    }
}