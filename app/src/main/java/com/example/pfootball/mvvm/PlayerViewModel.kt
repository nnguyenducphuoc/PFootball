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


class PlayerViewModel : ViewModel() {
    private var _playerLivedata = MutableLiveData<Player>()

    fun getPlayers(league: Int, season: Int, team: Int, page: Int?) {
        viewModelScope.launch {
            StandingInstance.api.getPlayer(league, season, team, page)
                .enqueue(object : Callback<Player> {
                    override fun onResponse(call: Call<Player>, response: Response<Player>) {
                        if (response.isSuccessful && response.body() != null) {
                            _playerLivedata.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<Player>, t: Throwable) {
                        Utilities.showLog(t.toString())
                    }

                })
        }
    }

    fun observerPlayerLivedata(): LiveData<Player> {
        return _playerLivedata
    }

}