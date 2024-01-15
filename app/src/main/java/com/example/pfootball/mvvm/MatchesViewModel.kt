package com.example.pfootball.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfootball.api.StandingInstance
import com.example.pfootball.models.matches.Matches
import com.example.pfootball.models.round.Round
import com.example.pfootball.myutils.Utilities
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchesViewModel : ViewModel() {
    private val _parentMatchesLiveData = MutableLiveData<Matches>()
    private val _parentRoundLiveData = MutableLiveData<Round>()
    private val _parentAllRoundLiveData = MutableLiveData<Round>()

    fun getMatchesFromRound(league: Int?, season: Int, round: String?) {
        viewModelScope.launch {
            StandingInstance.api.getMatchesFromRound(league, season, round)
                .enqueue(object : Callback<Matches> {

                    override fun onResponse(call: Call<Matches>, response: Response<Matches>) {
                        if (response.isSuccessful && response.body() != null) {
                            _parentMatchesLiveData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<Matches>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }
                })
        }
    }

    fun getRound(league: Int?, season: Int, current: Boolean?) {
        viewModelScope.launch {
            StandingInstance.api.getRound(league, season, current)
                .enqueue(object : Callback<Round> {

                    override fun onResponse(call: Call<Round>, response: Response<Round>) {
                        if (response.isSuccessful && response.body() != null) {
                            _parentRoundLiveData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<Round>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }
                })
        }
    }

    fun getAllRound(league: Int?, season: Int) {
        viewModelScope.launch {
            StandingInstance.api.getAllRound(league, season)
                .enqueue(object : Callback<Round> {

                    override fun onResponse(call: Call<Round>, response: Response<Round>) {
                        _parentAllRoundLiveData.value = response.body()
                    }

                    override fun onFailure(call: Call<Round>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }
                })
        }
    }

    fun observerMatchesLiveData(): LiveData<Matches> {
        return _parentMatchesLiveData
    }

    fun observerRoundLiveData(): LiveData<Round> {
        return _parentRoundLiveData
    }

    fun observerAllRoundLiveData(): LiveData<Round> {
        return _parentAllRoundLiveData
    }

}