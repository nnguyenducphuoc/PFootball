package com.example.pfootball.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pfootball.adapter.MatchesChildAdapter
import com.example.pfootball.databinding.FragmentMatchesBinding
import com.example.pfootball.models.matches.Response
import com.example.pfootball.mvvm.MatchesViewModel
import com.example.pfootball.myutils.Utilities
import kotlinx.coroutines.launch

class MatchesFragment : Fragment() {
    private lateinit var binding: FragmentMatchesBinding
    private lateinit var matchesViewModel: MatchesViewModel
    private lateinit var mResponseList: MutableList<Response>
    private lateinit var mRoundList: MutableList<String>
    private lateinit var mAllRoundList: MutableList<String>
    private lateinit var mMatchesChildAdapter: MatchesChildAdapter
    private lateinit var mRoundAdapter: ArrayAdapter<String>
    private var receivedData: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receivedData = arguments?.getInt("id")
        mResponseList = ArrayList()
        mRoundList = ArrayList()
        mAllRoundList = ArrayList()
        mMatchesChildAdapter = MatchesChildAdapter(mResponseList)
        matchesViewModel = ViewModelProvider(this)[MatchesViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMatches.setHasFixedSize(true)
        binding.recyclerViewMatches.layoutManager = LinearLayoutManager(context)
        matchesViewModel.getAllRound(receivedData, 2023)
        matchesViewModel.getRound(receivedData, 2023, true)

        setSpinnerSelected()
        observerAllRound()
        observerCurrentRound()
        observerMatches()
    }

    private fun observerAllRound() {
        matchesViewModel.observerAllRoundLiveData().observe(viewLifecycleOwner, Observer {
            mAllRoundList = it.response as MutableList<String>
        })
    }

    private fun setSpinnerSelected() {
        binding.roundSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // Xử lý khi một mục được chọn
                val selectedItem = mAllRoundList[position]
                matchesViewModel.getMatchesFromRound(
                    receivedData,
                    2023,
                    selectedItem
                )
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Được gọi khi không có mục nào được chọn
            }
        }
    }

    private fun observerMatches() {
        matchesViewModel.observerMatchesLiveData().observe(viewLifecycleOwner) {
            mResponseList = it.response as MutableList<Response>
            mMatchesChildAdapter = MatchesChildAdapter(mResponseList)
            binding.recyclerViewMatches.adapter = mMatchesChildAdapter
        }
    }


    private fun observerCurrentRound() {
        lifecycleScope.launch {
            matchesViewModel.observerRoundLiveData().observe(viewLifecycleOwner) {
                mRoundList = it.response as MutableList<String>
                getMatchRoundCurrent(mRoundList)
                initSpinner(it.response[0])
            }
        }
    }

    private fun initSpinner(current: String) {
        lifecycleScope.launch {
            mRoundAdapter = object : ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                mAllRoundList
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val season = getItem(position)
                    val view = super.getView(position, convertView, parent)
                    if (season != null) {
                        val textView = view.findViewById<TextView>(android.R.id.text1)
                        textView.text = "Vòng " + Utilities.extractNumber(season)
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
                        textView.text = "Vòng " + Utilities.extractNumber(season)
                    }
                    return view
                }
            }

            mRoundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.roundSpinner.adapter = mRoundAdapter
            binding.roundSpinner.setSelection(Utilities.extractNumber(current).toInt() - 1)
        }
    }

    private fun getMatchRoundCurrent(mRoundList: List<String>) {
        lifecycleScope.launch {
            matchesViewModel.getMatchesFromRound(
                receivedData,
                2023,
                mRoundList[0]
            )
        }
    }
}