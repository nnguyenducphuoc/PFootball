package com.example.pfootball.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfootball.api.StandingInstance
import com.example.pfootball.models.StandingModel
import com.example.pfootball.models.season.Season
import com.example.pfootball.myutils.Utilities
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingViewModel : ViewModel() {
    var selectedItemData: Int? = null
    private var _standingsLiveData = MutableLiveData<StandingModel>()
    private var _seasonLiveData = MutableLiveData<Season>()

    fun getStanding(league: Int?, season: Int, team: Int?) {
        viewModelScope.launch {
            StandingInstance.api.getStanding(league, season, team)
                .enqueue(object : Callback<StandingModel> {
                    override fun onResponse(
                        call: Call<StandingModel>,
                        response: Response<StandingModel>
                    ) {
                        if (response.body() != null && response.isSuccessful) {
                            _standingsLiveData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<StandingModel>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }

                })
        }
    }

    fun getSeason(id: Int?) {
        viewModelScope.launch {
            StandingInstance.api.getSeason(id).enqueue(object : Callback<Season> {
                override fun onResponse(call: Call<Season>, response: Response<Season>) {
                    if (response.body() != null && response.isSuccessful) {
                        _seasonLiveData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Season>, t: Throwable) {
                    Utilities.showLog(t.toString())
                }

            })
        }
    }


    fun observerStandingsLiveData(): LiveData<StandingModel> {
        return _standingsLiveData
    }

    fun observerSeasonLiveData(): LiveData<Season> {
        return _seasonLiveData
    }


}

