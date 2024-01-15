package com.example.pfootball.api

import com.example.pfootball.models.Player
import com.example.pfootball.models.StandingModel
import com.example.pfootball.models.matches.Matches
import com.example.pfootball.models.round.Round
import com.example.pfootball.models.season.Season
import com.example.pfootball.myutils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/standings")
    fun getStanding(
        @Query("league") league: Int?,
        @Query("season") season: Int,
        @Query("team") team: Int?
    ): Call<StandingModel>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/leagues")
    fun getSeason(
        @Query("id") id: Int?
    ): Call<Season>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/fixtures")
    fun getNextMatches(
        @Query("league") league: Int?,
        @Query("season") season: Int,
        @Query("next") next: Int
    ): Call<Matches>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/fixtures")
    fun getMatchesFromRound(
        @Query("league") league: Int?,
        @Query("season") season: Int,
        @Query("round") round: String?
    ): Call<Matches>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/players")
    fun getPlayer(
        @Query("league") league: Int?,
        @Query("season") season: Int,
        @Query("team") team: Int,
        @Query("page") page: Int?
    ): Call<Player>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/players/topscorers")
    fun getTopScore(
        @Query("league") league: Int?,
        @Query("season") season: Int,
    ): Call<Player>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/players/topassists")
    fun getTopAssists(
        @Query("league") league: Int?,
        @Query("season") season: Int,
    ): Call<Player>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/players/topyellowcards")
    fun getTopYellowCards(
        @Query("league") league: Int?,
        @Query("season") season: Int,
    ): Call<Player>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/players/topredcards")
    fun getTopRedCards(
        @Query("league") league: Int?,
        @Query("season") season: Int,
    ): Call<Player>


    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/fixtures/rounds")
    fun getRound(
        @Query("league") league: Int?,
        @Query("season") season: Int,
        @Query("current") current: Boolean?
    ): Call<Round>

    @Headers("x-rapidapi-key: " + Constants.HEADER)
    @GET("/fixtures/rounds")
    fun getAllRound(
        @Query("league") league: Int?,
        @Query("season") season: Int
    ): Call<Round>
}