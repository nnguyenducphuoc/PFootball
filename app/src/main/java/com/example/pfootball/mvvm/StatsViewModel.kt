package com.example.pfootball.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfootball.api.StandingInstance
import com.example.pfootball.models.Player
import com.example.pfootball.myutils.Utilities
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatsViewModel : ViewModel() {
    private var _statsTopScoresLiveData = MutableLiveData<Player>()
    private var _statsTopAssistsLiveData = MutableLiveData<Player>()
    private var _statsTopYellowCardLiveData = MutableLiveData<Player>()
    private var _statsTopRedCardLiveData = MutableLiveData<Player>()

    fun getTopScores(league: Int?, season: Int) {
        viewModelScope.launch {
            StandingInstance.api.getTopScore(league, season).enqueue(object : Callback<Player> {
                override fun onResponse(call: Call<Player>, response: Response<Player>) {
                    if (response.isSuccessful && response.body() != null) {
                        _statsTopScoresLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Player>, t: Throwable) {
                    Utilities.showLog(t.toString())
                }

            })
        }
    }

    fun getTopAssists(league: Int?, season: Int) {
        viewModelScope.launch {
            StandingInstance.api.getTopAssists(league, season).enqueue(object : Callback<Player> {
                override fun onResponse(call: Call<Player>, response: Response<Player>) {
                    if (response.isSuccessful && response.body() != null) {
                        _statsTopAssistsLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Player>, t: Throwable) {
                    Utilities.showLog(t.toString())
                }

            })
        }
    }

    fun getTopYellowCards(league: Int?, season: Int) {
        viewModelScope.launch {
            StandingInstance.api.getTopYellowCards(league, season)
                .enqueue(object : Callback<Player> {
                    override fun onResponse(call: Call<Player>, response: Response<Player>) {
                        if (response.isSuccessful && response.body() != null) {
                            _statsTopYellowCardLiveData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<Player>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }

                })
        }
    }

    fun getTopRedCard(league: Int?, season: Int) {
        viewModelScope.launch {
            StandingInstance.api.getTopRedCards(league, season).enqueue(object : Callback<Player> {
                override fun onResponse(call: Call<Player>, response: Response<Player>) {
                    if (response.isSuccessful && response.body() != null) {
                        _statsTopRedCardLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Player>, t: Throwable) {
                    Utilities.showLog(t.toString())
                }

            })
        }
    }

    fun observerTopScoreStatsLivedata(): LiveData<Player> {
        return _statsTopScoresLiveData
    }

    fun observerTopAssistStatsLivedata(): LiveData<Player> {
        return _statsTopAssistsLiveData
    }

    fun observerTopYellowCardStatsLivedata(): LiveData<Player> {
        return _statsTopYellowCardLiveData
    }

    fun observerTopRedCardLivedata(): LiveData<Player> {
        return _statsTopRedCardLiveData
    }
}